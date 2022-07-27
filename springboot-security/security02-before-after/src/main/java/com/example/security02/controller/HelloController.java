package com.example.security02.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Alishiz
 * @Date : 2022/7/23/0023
 * @email : 1575234570@qq.com
 * @Description :
 */
@RestController
@RequestMapping("hello")
public class HelloController {
    @GetMapping("hello")
    public String hello(){
        return "Hello Security!!!";
    }
}
