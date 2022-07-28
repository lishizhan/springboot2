package com.example.security04traditionweb.controller;

import com.example.security04traditionweb.entity.User;
import com.example.security04traditionweb.service.UserService;
import com.example.security04traditionweb.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.http.SecurityHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author : Alishiz
 * @Date : 2022/7/27/0027
 * @email : 1575234570@qq.com
 * @Description :
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public R user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return R.ok().put("authentication", authentication);

    }

    @GetMapping("/users")
    public R users() {
        List<User> users = userService.getBaseMapper().selectList(null);
        return R.ok().put("data", users);

    }
}
