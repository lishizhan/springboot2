package com.example.springbootredis.service.impl;

import com.example.springbootredis.entity.User;
import com.example.springbootredis.mapper.UserMapper;
import com.example.springbootredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUsers() {
        return userMapper.selectUserAll();
    }
}
