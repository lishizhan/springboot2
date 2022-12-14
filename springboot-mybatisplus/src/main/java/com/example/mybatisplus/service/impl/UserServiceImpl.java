package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.dao.UserMapper;
import com.example.mybatisplus.entity.User;
import com.example.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        return userMapper.insert(user);

    }

    @Override
    public List<User> selectAll() {

        List<User> users = userMapper.selectList(null);
        return users;


    }
}
