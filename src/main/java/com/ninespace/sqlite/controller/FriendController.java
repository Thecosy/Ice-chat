package com.ninespace.sqlite.controller;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ninespace.sqlite.entity.Friend;
import com.ninespace.sqlite.entity.Group;
import com.ninespace.sqlite.entity.OfflineMsg;
import com.ninespace.sqlite.entity.User;
import com.ninespace.sqlite.mapper.FriendMapper;
import com.ninespace.sqlite.mapper.GroupMapper;
import com.ninespace.sqlite.mapper.OfflineMsgMapper;
import com.ninespace.sqlite.mapper.UserMapper;
import com.ninespace.sqlite.service.FriendService;
import com.ninespace.sqlite.util.RedisUtil;
import com.ninespace.sqlite.vo.Result;
import com.ninespace.sqlite.vo.UserGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Api(tags = "friend")
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FriendService friendService;

    @ApiOperation(value = "根据id添加朋友")
    @GetMapping("/addFriendById/{userId}/{friendId}")
    public Result addFriendById(
            @PathVariable Integer userId,
            @PathVariable Integer friendId
    ){
        //先查询是否有此好友，如果有就不做任何事
        QueryWrapper<Friend> queryWrapperCount = new QueryWrapper<Friend>();
        queryWrapperCount.eq("friendId",friendId);
        queryWrapperCount.eq("userId",userId);
        Integer integer = friendMapper.selectCount(queryWrapperCount);
        if(integer >= 1){
            return Result.other();
        }

        Friend friend = new Friend();
        friend.setUserId(userId);
        friend.setFriendId(friendId);

        //临时
        //无需对方确认，即可添加好友
        //后期在数据库添加状态字段即可
        Friend OnFriend = new Friend();
        OnFriend.setUserId(friendId);
        OnFriend.setFriendId(userId);

        try {
            friendMapper.insert(OnFriend);
            friendMapper.insert(friend);

            //查询一下数据更新最新缓存
            friendService.getFriendList(friendId);
            friendService.getFriendList(userId);
            return Result.ok();
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @ApiOperation(value = "根据电话号码查询朋友")
    @GetMapping("/searchFriendByPhone/{phone}")
    public Result searchFriendByPhone(
            @PathVariable Long phone
    ) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("phone",phone);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            return Result.error();
        }
        return Result.ok().data("user",user);
    }

    @ApiOperation(value = "根据电话号码添加朋友")
    @GetMapping("/addFriendByPhone/{userId}/{phone}")
    public Result addFriendByPhone(
            @PathVariable Integer userId,
            @PathVariable Long phone
    ){

        Friend friend = new Friend();
        friend.setUserId(userId);
        //根据电话号码查询id
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("phone",phone);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            return Result.error();
        }
        friend.setFriendId(user.getId());

        if(user.getId().equals(userId)){
            return Result.other().data("不能添加自己",null);
        }

        //临时
        //无需对方确认，即可添加好友
        //后期在数据库添加状态字段即可
        Friend OnFriend = new Friend();
        OnFriend.setUserId(user.getId());
        OnFriend.setFriendId(userId);

        //先查询是否有此好友，如果有就不做任何事
        QueryWrapper<Friend> queryWrapperCount = new QueryWrapper<Friend>();
        queryWrapperCount.eq("friendId",user.getId());
        queryWrapperCount.eq("userId",userId);
        Integer integer = friendMapper.selectCount(queryWrapperCount);
        if(integer >= 1){
            return Result.other();
        }
        try {
            friendMapper.insert(OnFriend);
            friendMapper.insert(friend);

            //查询一下数据更新最新缓存
            friendService.getFriendList(userId);
            return Result.ok();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation(value = "根据id查询朋友和群组目录")
    @GetMapping("/getFriendByUserId/{userid}")
    public Result getFriendByUserId(
            @PathVariable Integer userid
    ) throws ParseException {
        //先看看有没有缓存，有则返回缓存数据
        Object userListCache = redisUtil.get("userList." + userid);
        if(userListCache != null){
            return Result.ok().data("result",userListCache);
        }
        JSONObject friendList = friendService.getFriendList(userid);
        return Result.ok().data("result",friendList);
    }

    @ApiOperation(value = "根据id查询朋友目录")
    @GetMapping("/getOnlyFriendByUserId/{userid}")
    public Result getOnlyFriendByUserId(
            @PathVariable Integer userid
    ) throws ParseException {
        //先看看有没有缓存，有则返回缓存数据
        Object userListCache = redisUtil.get("userOnlyList." + userid);
        if(userListCache != null){
            return Result.ok().data("result",userListCache);
        }
        JSONObject friendList = friendService.getOnlyFriendList(userid);
        return Result.ok().data("result",friendList);
    }

    @ApiOperation(value = "暗号查找朋友")
    @GetMapping("/setSignal/{userid}/{signal}")
    public Result setSignal(
            @PathVariable Integer userid,
            @PathVariable Integer signal

    ) throws ParseException {
        //查询是否重复
        //存入redis
        List<User> result = new ArrayList<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("id",userid);
        User user = userMapper.selectOne(queryWrapper);

        result.add(user);
        redisUtil.sSet("signal." + signal,user);
        redisUtil.expire("signal." + signal,120);

        //先看看有没有缓存，有则返回缓存数据
        Set<Object> signalRedis = redisUtil.sGet("signal." + signal);
        if(signalRedis != null){
            return Result.ok().data("result",signalRedis);
        }
        return Result.error();
    }


}
