package com.ninespace.sqlite.service.impl;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ninespace.sqlite.entity.Friend;
import com.ninespace.sqlite.entity.Group;
import com.ninespace.sqlite.entity.OfflineMsg;
import com.ninespace.sqlite.entity.User;
import com.ninespace.sqlite.mapper.FriendMapper;
import com.ninespace.sqlite.mapper.GroupMapper;
import com.ninespace.sqlite.mapper.OfflineMsgMapper;
import com.ninespace.sqlite.mapper.UserMapper;
import com.ninespace.sqlite.service.FriendService;
import com.ninespace.sqlite.service.OfflineMsgService;
import com.ninespace.sqlite.util.RedisUtil;
import com.ninespace.sqlite.vo.UserGroup;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private OfflineMsgMapper offlineMsgMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public JSONObject getFriendList(Integer userid) {

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
//                        redisUtil.set("offlineMsg", JSON.toJSONString(offlineMsg));
//                        Object object = redisUtil.get("offlineMsg");
//                        if (null != object) {
//                            return JSON.parseObject(object.toString(), new TypeReference<List<OfflineMsg>>() {});
//                        }

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

        //缓存
        redisUtil.set("userList."+userid,jsonObject);
        //指定缓存失效时间   2分钟
        redisUtil.expire("userList."+userid,120);

        return jsonObject;
    }

    @Override
    public JSONObject getOnlyFriendList(Integer userid) {

        QueryWrapper<Friend> queryWrapper = new QueryWrapper<Friend>();
        queryWrapper.eq("userId",userid);
        List<Friend> friends = friendMapper.selectList(queryWrapper);

        List<UserGroup> result = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();

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

        //缓存
        redisUtil.set("userOnlyList."+userid,jsonObject);
        //指定缓存失效时间   2分钟
        redisUtil.expire("userOnlyList."+userid,120);

        return jsonObject;
    }
}