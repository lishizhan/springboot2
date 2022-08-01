package com.example.security07.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.security07.common.RedisConstants;
import com.example.security07.dto.LoginDto;
import com.example.security07.dto.UserDto;
import com.example.security07.entity.User;
import com.example.security07.service.UserService;
import com.example.security07.mapper.UserMapper;
import com.example.security07.utils.JWTUtil;
import com.example.security07.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public R login(LoginDto loginDto) {
        //获取AuthenticationManager 进行用户认证
        Principal principal;
        Object credentials;
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        //传入security的认证管理器，它会调用com.example.security07.service.impl.UserServiceDBImpl.loadUserByUsername的方法
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证失败进行异常提示
        if (Objects.isNull(authenticate)) throw new UsernameNotFoundException("用户名或密码错误");
        //如果认证通过生成JWT
        UserDto UserDto = (UserDto) authenticate.getPrincipal();
        String userId = UserDto.getUser().getId().toString();
        Map<String, String> map = new HashMap<>();
        map.put("id", userId);
        String token = JWTUtil.getToken(map);

        //将用户信息存入Redis
        stringRedisTemplate.opsForValue().set(RedisConstants.LOGIN_USER_KEY + userId, JSON.toJSONString(UserDto));
        //根据用户id生成的token返回给前端
        return R.ok().put("token", token);
    }

    @Override
    public R logout() {
        //获取SecurityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto principal = (UserDto) authentication.getPrincipal();
        Long id = principal.getUser().getId();
        //删除reids中的用户登陆信息
        Boolean delete = stringRedisTemplate.delete(RedisConstants.LOGIN_USER_KEY + id);
        return delete ? R.ok("注销成功") : R.error("注销失败");
    }
}




