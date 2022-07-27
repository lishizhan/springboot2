package com.example.security03.config.security;

import com.example.security03.entity.Role;
import com.example.security03.entity.User;
import com.example.security03.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/7/27 16:55
 */
@Component
public class UserDetailDbService implements UserDetailsService {


    private UserMapper userMapper;
    @Autowired
    public UserDetailDbService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (ObjectUtils.isEmpty(user)){
            throw  new UsernameNotFoundException("用户名不正确");
        }

        List<Role> roles = userMapper.getRoleById(user.getId());
        user.setRoles(roles);
        return user;
    }
}
