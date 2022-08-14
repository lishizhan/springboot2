package com.example.demo02.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo02.entity.User;
import com.example.demo02.service.UserService;
import com.example.demo02.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        //查询用户信息
        User user = this.getOne(new LambdaQueryWrapper<User>().eq(User::getUname, username));
        //查询权限信息在MyUserDetailService中判断用户存在后查询
        return  user;
    }
}




