package com.lishizhan.aliblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lishizhan.aliblog.entity.User;
import com.lishizhan.aliblog.service.UserService;
import com.lishizhan.aliblog.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




