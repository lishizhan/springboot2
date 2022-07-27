package com.example.security02.controller;

import com.example.security02.dto.R;
import com.example.security02.dto.UserLoginVo;
import com.example.security02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Alishiz
 * @Date : 2022/7/25/0025
 * @email : 1575234570@qq.com
 * @Description :
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public R login(UserLoginVo userLoginVo) {
        return userService.getUserList();
    }

    @PostMapping("/login")
    public R login(){
        return R.ok("登录成功");
    }

}
