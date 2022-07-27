package com.example.security01.service.impl;

import com.example.security01.dao.UserMapper;
import com.example.security01.dto.R;
import com.example.security01.entity.UserEntity;
import com.example.security01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : Alishiz
 * @Date : 2022/7/25/0025
 * @email : 1575234570@qq.com
 * @Description :
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public R login(String phone_or_email, String password) {

        List<UserEntity> list = userMapper.selectList(null);

        return R.ok().put("users", list);
    }

    @Override
    public R getUserList() {
        List<UserEntity> list = userMapper.selectList(null);
        return R.ok().put("data", list);
    }

}
