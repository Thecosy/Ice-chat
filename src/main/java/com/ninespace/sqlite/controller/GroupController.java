package com.ninespace.sqlite.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import com.ninespace.sqlite.service.GroupService;
import com.ninespace.sqlite.util.RedisUtil;
import com.ninespace.sqlite.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.text.ParseException;
import java.util.*;

@RestController
@Api(tags = "group")
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private GroupService groupService;

    @Autowired
    private OfflineMsgMapper offlineMsgMapper;

    @Autowired
    private FriendService friendService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "搜索群聊")
    @GetMapping("/searchGroupByName/{name}")
    public Result searchGroupByName(
            @PathVariable String name
    ){
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("groupName",name);
        List<Group> groups = groupMapper.selectList(queryWrapper);

        //如果找不到名字，那么就是要找id
        if (groupMapper.selectCount(queryWrapper) == 0){
            QueryWrapper<Group> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("id",name);
            Group groups1 = groupMapper.selectOne(queryWrapper1);

            //id也找不到就返回other
            if (groupMapper.selectCount(queryWrapper1) == 0){
                return Result.other();
            }
            return Result.ok().data("result",groups1);
        }
        return Result.ok().data("result",groups);
    }

    @ApiOperation(value = "创建群聊")
    @GetMapping("/addGroupById/{userId}/{groupName}")
    public Result addFriendById(
            @PathVariable Long userId,
            @PathVariable String groupName
    ){
        Group group = new Group();
        group.setGroupName(groupName);
        group.setGroupOwner(userId);
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(userId);
        String s = arrayList.toString();
        group.setMembers(s);
        groupMapper.insert(group);

        return Result.ok();
    }

    @ApiOperation(value = "查看群主的群聊")
    @GetMapping("/getGroupOwnerByUserId/{userid}")
    public Result getFriendByUserId(
            @PathVariable Integer userid
    ){
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("groupOwner",userid);
        List<Group> groups = groupMapper.selectList(queryWrapper);
        return Result.ok().data("result",groups);
    }

    @ApiOperation(value = "查看用户加入的群聊")
    @GetMapping("/getGroupinfoByUserId/{groupId}")
    public Result getGroupinfoByUserId(
            @PathVariable Integer groupId
    ){
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",groupId);
        Group groups = groupMapper.selectOne(queryWrapper);
        return Result.ok().data("result",groups);
    }

    @ApiOperation(value = "添加群聊")
    @GetMapping("/addGroupMemberByUserId/{userid}/{groupID}")
    public Result addGroupMemberByUserId(
            @PathVariable Integer userid,
            @PathVariable Long groupID

    ){
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",groupID);
        Group one = groupService.getOne(queryWrapper);
        String members = one.getMembers();

        ArrayList tempList = new ArrayList();;

        //将json字符串转为对象
        JSONArray jsArr = JSONObject.parseArray(members);
        if(jsArr != null){
        for (Object o : jsArr) {
            tempList.add(o);
        }
        }
        // linkedHashSetList去重
        ArrayList<Integer> linkedHashSetList = new ArrayList<>(new LinkedHashSet<>(tempList));

        linkedHashSetList.add(userid);

        System.out.println(tempList);
        //将对象转为json字符串
        String json3 = JSONObject.toJSONString(linkedHashSetList);

        //

        Group group = new Group();
        group.setMembers(json3);

        groupMapper.update(group,queryWrapper);

        //查询一下数据更新最新缓存
        friendService.getFriendList(userid);
        return Result.ok();
    }

    @ApiOperation(value = "获得全部群聊表")
    @GetMapping("/getGroup")
    public List<OfflineMsg> getGroup(){
        return offlineMsgMapper.selectList(null);
    }

    @ApiOperation(value = "根据id查询群聊目录")
    @GetMapping("/getOnlyGroupListByUserId/{userid}")
    public Result getOnlyGroupListByUserId(
            @PathVariable Integer userid
    ) throws ParseException {
        //先看看有没有缓存，有则返回缓存数据
        Object groupListCache = redisUtil.get("groupOnlyList." + userid);
        if(groupListCache != null){
            return Result.ok().data("result",groupListCache);
        }
        cn.hutool.json.JSONObject friendList = groupService.getOnlyGroupList(userid);
        return Result.ok().data("result",friendList);
    }

}
