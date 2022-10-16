package com.ninespace.sqlite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ninespace.sqlite.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: spring-data-sqlite
 * @description:
 * @author: hewei
 * @date: 2021-10-21 17:40
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {
    List<User> selectByAddr(@Param("addr") String addr);
}
