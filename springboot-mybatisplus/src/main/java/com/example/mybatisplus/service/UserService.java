package com.example.mybatisplus.service;

import com.example.mybatisplus.entity.User;

import java.util.List;

public interface UserService {
    int insert(User user);

    List<User> selectAll();

}
