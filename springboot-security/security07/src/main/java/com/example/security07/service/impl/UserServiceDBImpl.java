package com.example.security07.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.security07.dto.UserDto;
import com.example.security07.entity.Role;
import com.example.security07.entity.User;
import com.example.security07.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author : Alishiz
 * @Date : 2022/7/29/0029
 * @email : 1575234570@qq.com
 * @Description :
 */
@Service
@Slf4j
public class UserServiceDBImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //在数据库查询用户信息
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, s));
        if (Objects.isNull(user)) throw new UsernameNotFoundException("用户或错误");
        //TODO 查询用户权限
        List<Role> roles = userMapper.getRoleById(user.getId());
        //返回UserDetails的自定义实现类
        UserDto userDto = new UserDto();
        userDto.setUser(user);
        userDto.setRoles(roles);
        return userDto;
    }
}
