package com.example.security04traditionweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.security04traditionweb.entity.Role;
import com.example.security04traditionweb.entity.User;
import com.example.security04traditionweb.service.UserService;
import com.example.security04traditionweb.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;


    /**
     *https://blog.csdn.net/K_520_W/article/details/118855281
     * */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //查询数据库
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, s));
        if (ObjectUtils.isEmpty(user)) throw new UsernameNotFoundException("用户不存在");
        //获取角色
        List<Role> roles = userMapper.getRoleById(user.getId());
        user.setRoles(roles);
        return user;
    }
}




