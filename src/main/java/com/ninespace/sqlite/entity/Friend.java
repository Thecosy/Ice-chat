package com.ninespace.sqlite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @program: spring-data-sqlite
 * @description:
 * @author: hewei
 * @date: 2021-10-21 17:42
 **/
@Data
@TableName(schema = "main",value = "c_friend")
public class Friend {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private Integer userId;
    private Integer friendId;
}
