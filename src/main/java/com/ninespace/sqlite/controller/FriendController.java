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
    private UserMapper userMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private OfflineMsgMapper offlineMsgMapper;

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
            return Result.ok();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation(value = "根据id查询朋友")
    @GetMapping("/getFriendByUserId/{userid}")
    public Result getFriendByUserId(
            @PathVariable Integer userid
    ) throws ParseException {
        QueryWrapper<Friend> queryWrapper = new QueryWrapper<Friend>();
        queryWrapper.eq("userId",userid);
        List<Friend> friends = friendMapper.selectList(queryWrapper);

        List<UserGroup> result = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();

        //查询此用户所有的群聊
        List<Group> groups = groupMapper.selectList(null);
        for (Group group : groups) {
            String members = group.getMembers();
            //将json字符串转为对象
            if (!members.equals("")) {
                JSONArray jsArr = com.alibaba.fastjson.JSONObject.parseArray(members);
                for (Object o : jsArr) {
                    if (o == userid) {
                        UserGroup user = new UserGroup();
                        user.setName(group.getGroupName());
                        user.setProfile(group.getProfile());
                        user.setType("2");
                        user.setId(Math.toIntExact(group.getId()));

                        UserGroup userGroup = new UserGroup();
                        userGroup.setType("2");
                        BeanUtils.copyProperties(user,userGroup);

                        //根据房间号查询redis用户有多少未读消息
                        QueryWrapper<OfflineMsg> offlineMsgWrapper = new QueryWrapper<OfflineMsg>().orderByDesc("id").eq("type",2).eq("toId",userid).eq("groupId", Math.toIntExact(group.getId()));
                        Integer count = offlineMsgMapper.selectCount(offlineMsgWrapper);
                        userGroup.setUnMessages(count);
                        //添加最新消息的内容和时间
                        List<OfflineMsg> offlineMsg = offlineMsgMapper.selectList(offlineMsgWrapper);
                        if(count !=0 ) {
                            OfflineMsg msg = offlineMsg.get(0);
                            userGroup.setNewMessages(msg.getMsg());
                            userGroup.setNewTime(msg.getTime());
                        }

                        result.add(userGroup);
                    }
                }
            }
        }
        for (Friend friend : friends) {
            //根据id查询用户信息
            User user = userMapper.selectById(friend.getFriendId());
            UserGroup userGroup = new UserGroup();
            userGroup.setType("1");
            BeanUtils.copyProperties(user,userGroup);

            //根据房间号查询redis用户有多少未读消息
            QueryWrapper<OfflineMsg> offlineMsgWrapper = new QueryWrapper<OfflineMsg>().orderByDesc("id").eq("type",1).eq("toId", userid);
            Integer count = offlineMsgMapper.selectCount(offlineMsgWrapper);
            userGroup.setUnMessages(count);
            //添加最新消息的内容和时间
            List<OfflineMsg> offlineMsg = offlineMsgMapper.selectList(offlineMsgWrapper);
            if(count !=0 ) {
                OfflineMsg msg = offlineMsg.get(0);
                userGroup.setNewMessages(msg.getMsg());
                userGroup.setNewTime(msg.getTime());
            }

            result.add(userGroup);
        }
        jsonObject.set("user",result);
        return Result.ok().data("result",jsonObject);
    }

}
