package com.ninespace.sqlite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.w3c.dom.Text;

/**
 * @program: spring-data-sqlite
 * @description:
 * @author: hewei
 * @date: 2021-10-21 17:42
 **/
@Data
@TableName(schema = "main",value = "c_group")
public class Group {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String groupName;
    private Long groupOwner;
    private String members;
    private String profile;
    private String intro;
}
