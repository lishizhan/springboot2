package com.example.demo02.config.security;

import com.example.demo02.entity.Role;
import com.example.demo02.entity.User;
import com.example.demo02.entity.dto.UserDto;
import com.example.demo02.service.RoleService;
import com.example.demo02.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @Author : lishizhan
 * @Date : 2022/8/13/0013
 * @email : 1575234570@qq.com
 * @Description :
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名查询数据是否有该用户
        User userDb = userService.getUserByUsername(username);
        Optional.ofNullable(userDb).orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
        //用户存在则查询用户权限信息
        List<Role> roles =  roleService.getUserRolesByUserId(userDb.getId());
        userDb.setRoles(roles);
        //对象拷贝
        UserDto userDto = new UserDto();
        userDto.setUser(userDb);
        return userDto;
    }
}
