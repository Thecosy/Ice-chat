package com.ninespace.sqlite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ninespace.sqlite.entity.Friend;
import com.ninespace.sqlite.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendMapper extends BaseMapper<Friend> {
}
