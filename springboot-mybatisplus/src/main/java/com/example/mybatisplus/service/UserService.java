package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    int insert(User user);

    List<User> selectAll();

}
