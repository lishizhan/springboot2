package com.example.security07.controller;

import com.example.security07.service.UserService;
import com.example.security07.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Alishiz
 * @Date : 2022/7/30/0030
 * @email : 1575234570@qq.com
 * @Description :
 */
@RestController
public class LogoutController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/logout")
    public R logout(){
        return userService.logout();
    }

}
