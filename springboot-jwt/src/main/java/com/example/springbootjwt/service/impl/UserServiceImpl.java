package com.example.springbootjwt.service.impl;

import com.example.springbootjwt.dao.UserMapper;
import com.example.springbootjwt.pojo.User;
import com.example.springbootjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user)  throws RuntimeException {
        User login = userMapper.login(user);
        if (login==null){
            new RuntimeException("用户名或密码错误");
        }
        return login;
    }

    @Override
    public User getUser(Long id1) {
        User user = userMapper.selectById(id1);
        user.setPwd(null);
        return user;
    }

    @Override
    public List<User> selectUserAll() {
        return userMapper.selectList(null);
    }
}
