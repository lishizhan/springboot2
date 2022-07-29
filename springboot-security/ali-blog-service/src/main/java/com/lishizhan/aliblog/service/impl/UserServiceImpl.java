package com.lishizhan.aliblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lishizhan.aliblog.common.R;
import com.lishizhan.aliblog.dto.UserRegisterDto;
import com.lishizhan.aliblog.entity.User;
import com.lishizhan.aliblog.mapper.UserMapper;
import com.lishizhan.aliblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public R register(UserRegisterDto userRegisterDto) {
        log.info("userRegisterDto-->{}",userRegisterDto);
        User user = new User();
        BeanUtils.copyProperties(userRegisterDto,user);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        log.info("user--->{}",user);
        int insert = userMapper.insert(user);
        return R.ok();
    }
}




