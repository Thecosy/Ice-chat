package com.ninespace.sqlite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(schema = "main",value = "offline_msg")
public class OfflineMsg{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private String id;
    private String fromId;
    private String toId;
    private String msg;
    private String time;
    private Integer type;
    private String groupId;
    private String profile;

}

