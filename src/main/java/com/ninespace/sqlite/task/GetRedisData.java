package com.ninespace.sqlite.task;

import com.ninespace.sqlite.controller.UserController;
import com.ninespace.sqlite.entity.User;
import com.ninespace.sqlite.mapper.UserMapper;
import com.ninespace.sqlite.util.RedisUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GetRedisData {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisUtil redisUtils;

    public void getData(){
        System.out.println("开启刷新热点数据");
        //系统启动中。。。从数据库获取值
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            //存入redis，以便于后面都从redis获取值，而不是每次从数据库直接查询
            redisUtils.set(user.getUsername(),user.getProfile());
        }
    }

    @PreDestroy
    public void destroy() {
        //系统运行结束
    }

    @Scheduled(cron = "0/60 0/1 * * * ? ")
    public void getDataSchedul() {
        //每隔10秒执行一次
        getData();
    }

}
