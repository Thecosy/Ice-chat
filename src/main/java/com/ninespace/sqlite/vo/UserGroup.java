package com.ninespace.sqlite.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @program: spring-data-sqlite
 * @description:
 * @author: hewei
 * @date: 2021-10-21 17:42
 **/
@Data
@TableName(schema = "main",value = "c_user")
public class UserGroup {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private Long phone;
    private String addr;
    private String gender;
    private String intro;
    private String profile;
    private String email;
    private String username;
    private String password;
    private String type;
    private Integer unMessages;
    private String newMessages;
    private String newTime;
}
