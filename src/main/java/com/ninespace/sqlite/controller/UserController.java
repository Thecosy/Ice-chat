package com.ninespace.sqlite.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.ninespace.sqlite.entity.OfflineMsg;
import com.ninespace.sqlite.mapper.OfflineMsgMapper;
import com.ninespace.sqlite.mapper.UserMapper;
import com.ninespace.sqlite.entity.User;
import com.ninespace.sqlite.service.OfflineMsgService;
import com.ninespace.sqlite.service.UserService;
import com.ninespace.sqlite.util.RedisUtil;
import com.ninespace.sqlite.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.List;

/**
 * @program: spring-data-sqlite
 * @description:
 * @author: hewei
 * @date: 2021-10-25 17:22
 **/
@RestController
@Api(tags = "user")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private OfflineMsgMapper offlineMsgMapper;

    @Autowired
    private OfflineMsgService offlineMsgService;

    @ApiOperation(value = "根据id获取用户信息")
    @GetMapping("/GetUserInfoById/{id}")
    public User GetUserInfoById(
            @PathVariable Long id
    ){
        return userMapper.selectById(id);
    }

    @ApiOperation(value = "根据电话号码登陆")
    @GetMapping("/phoneLogin/{phone}/0000")
    public Result phoneLogin(
            @PathVariable Long phone
    ){
        //检测用户手机号位数
        if(phone.toString().length() != 11){
            return Result.error();
        }
        //检测用户手机号是否已经存在
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        Integer integer = userMapper.selectCount(queryWrapper);
        if(integer != 1){
            User user = new User();
            user.setName("新用户");
            user.setIntro("这个人很懒，没有留下简介");
            user.setPhone(phone);
            user.setProfile("https://img2.woyaogexing.com/2022/08/27/6807ea2da585504c!400x400.jpg");
            userService.save(user);
            return Result.ok().data("user", user);
        }
        User user = userMapper.selectOne(queryWrapper);
        return Result.ok().data("user", user);
    }

//    @ApiOperation(value = "根据用户名登陆")
//    @GetMapping("/Login/{username}")
//    public User Login(
//            @PathVariable String username
//    ){
//        QueryWrapper queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username",username);
//        return userMapper.selectOne(queryWrapper);
//    }

//    @PostMapping("/selectByAddr")
//    public List<User> selectByAddr(String addr,Integer page,Integer pageSize){
//        PageHelper.startPage(page,pageSize);
//        return userMapper.selectByAddr(addr);
//    }

//    @GetMapping("/index")
//    public ResponseEntity<String> index(){
//        redisUtil.set("aaa","sss");
//        System.out.println(redisUtil.get("aaa"));
//        return ResponseEntity.ok("请求成功");
//    }

//    @ApiOperation(value = "根据名称添加")
//    @GetMapping("add/{name}")
//    public int add(
//            @PathVariable("name") String name
//    ) {
//        OfflineMsg offlineMsg = new OfflineMsg();
//        offlineMsg.setMsg(name);
//        int insert = offlineMsgMapper.insert(offlineMsg);
//        return insert;
//    }

//    @GetMapping("/addtest")
//    public boolean addtest(
//
//    ) {
//        OfflineMsg offlineMsg = new OfflineMsg();
//        offlineMsg.setFromId("123");
//        offlineMsg.setToId("toUsername");
//        offlineMsg.setMsg("text");
//        boolean save = this.offlineMsgService.save(offlineMsg);
//        return save;
//    }

//    @GetMapping("/getMsg")
//    public List<OfflineMsg> getMsg(
//    ) {
//        List<OfflineMsg> offlineMsgs = offlineMsgMapper.selectList(null);
//        return offlineMsgs;
//    }
}
