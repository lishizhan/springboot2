package com.example.demo04.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : lishizhan
 * @Date : 2022/8/14/0014
 * @email : 1575234570@qq.com
 * @Description :
 */
@RestController
public class AuthController {
    @GetMapping("/admin")
    public String admin() {
        return "admin ok";
    }

    @GetMapping("/user")
    public String user() {
        return "user ok";
    }

    @GetMapping("/getInfo")
    public String getInfo() {
        return "info ok";
    }
}
