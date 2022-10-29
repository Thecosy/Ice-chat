package com.ninespace.sqlite.service;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ninespace.sqlite.entity.Friend;
import com.ninespace.sqlite.entity.Group;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupService extends IService<Group> {
    JSONObject getOnlyGroupList(Integer userid);
}
