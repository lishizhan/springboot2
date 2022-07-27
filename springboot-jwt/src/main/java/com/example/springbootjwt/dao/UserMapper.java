package com.example.springbootjwt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootjwt.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User login(User user);
}
