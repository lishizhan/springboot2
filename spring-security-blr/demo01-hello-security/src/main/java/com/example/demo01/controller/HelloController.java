package com.example.demo01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/8/11 15:23
 */
@RestController
public class HelloController {
    @GetMapping("hello")
    public String hello(){
        return "Hello Security!!!";
    }
}
