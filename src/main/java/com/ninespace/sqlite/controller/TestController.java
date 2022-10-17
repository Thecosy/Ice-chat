package com.ninespace.sqlite.controller;


import com.ninespace.sqlite.entity.User;
import com.ninespace.sqlite.service.UserService;
import com.ninespace.sqlite.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "测试接口")
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    //采用线程池技术来生成数据。部分参数参考自己的配置进行设置。
    // 我这里采用核心线程数10，最大线程数20，阻塞队列容量10w
    // 拒绝策略CallerRunsPolicy的参数来生成。
    @ApiOperation(value = "生成测试数据")
    @GetMapping("/create}")
    public Integer createData(Integer password) {
        if (password != 1024) {
            return 0;
        }
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10,
                20, 1, TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(100000),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        int max = 1000000;
        for (int i = 0; i < max; i++) {
            poolExecutor.execute(() -> {
                Date now = new Date();
                User user = new User();
//                user.setStatus(0);
//                user.setUserName(UUID.randomUUID().toString().replace("-", ""));
//                user.setAddress("");
//                user.setCreateTime(now);
//                user.setUpdateTime(now);
                userService.save(user);
            });
        }
        return 1;
    }
}
