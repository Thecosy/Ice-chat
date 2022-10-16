package com.ninespace.sqlite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ninespace.sqlite.entity.Friend;
import com.ninespace.sqlite.entity.Group;
import com.ninespace.sqlite.mapper.FriendMapper;
import com.ninespace.sqlite.mapper.GroupMapper;
import com.ninespace.sqlite.service.FriendService;
import com.ninespace.sqlite.service.GroupService;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {
}