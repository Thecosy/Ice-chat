package com.ninespace.sqlite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ninespace.sqlite.entity.Friend;
import com.ninespace.sqlite.entity.OfflineMsg;
import com.ninespace.sqlite.mapper.FriendMapper;
import com.ninespace.sqlite.mapper.OfflineMsgMapper;
import com.ninespace.sqlite.service.FriendService;
import com.ninespace.sqlite.service.OfflineMsgService;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {
}