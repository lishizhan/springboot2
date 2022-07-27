package com.example.springbootjwt.service;

import com.example.springbootjwt.pojo.User;

import java.util.List;

public interface UserService {
    User login(User user);

    User getUser(Long id1);

    List<User> selectUserAll();

}
