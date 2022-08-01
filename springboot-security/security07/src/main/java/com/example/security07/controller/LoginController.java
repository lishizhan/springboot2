package com.example.security07.controller;

import com.example.security07.dto.LoginDto;
import com.example.security07.dto.UserDto;
import com.example.security07.service.UserService;
import com.example.security07.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Alishiz
 * @Date : 2022/7/30/0030
 * @email : 1575234570@qq.com
 * @Description :
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public R login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }

}
