package com.lishizhan.aliblog.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lishizhan.aliblog.common.RegexUtils;
import com.lishizhan.aliblog.entity.Role;
import com.lishizhan.aliblog.entity.User;
import com.lishizhan.aliblog.mapper.RoleMapper;
import com.lishizhan.aliblog.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @Author : Alishiz
 * @Date : 2022/7/28/0028
 * @email : 1575234570@qq.com
 * @Description :
 */
@Service
public class UserDtoService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = null;
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //判断是否为手机号登陆
        if (!RegexUtils.isPhoneInvalid(s)) {
             user = userMapper.selectOne(wrapper.eq(User::getPhone, s));
        }
        //判断是否为邮箱登陆
        if (!RegexUtils.isEmailInvalid(s)) {
            user = userMapper.selectOne(wrapper.eq(User::getEmail,s));
        }
        //用户不存在
        if (ObjectUtils.isEmpty(user)) throw new UsernameNotFoundException("用户不存在");

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        //获取角色
        List<Role> roles = roleMapper.getRoleById(user.getId());
        userDto.setRoles(roles);
        return userDto;
    }
}
