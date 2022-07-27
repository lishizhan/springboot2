package com.example.mybatisplus.controller;

import com.example.mybatisplus.dao.UserMapper;
import com.example.mybatisplus.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("hello/{id}")
    public User hello(@PathVariable("id") Long id){
        return userMapper.selectUserById(id);
    }

    /**
     * 查询所有用户
     * */
    @GetMapping("getUserList")
    public List<User> selectUserList(){
        return userMapper.selectList(null);
    }








}