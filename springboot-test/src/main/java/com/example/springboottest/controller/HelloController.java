package com.example.springboottest.controller;

import com.example.springboottest.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Alishiz
 * @Date : 2022/6/6/0006
 * @email : 1575234570@qq.com
 * @Description :
 */
@RestController
public class HelloController {
    @Autowired
    private Car car;

    @GetMapping("hello")
    public String hello() {
        return "Hello Spring Boot";
    }
    @GetMapping("car")
    public Car car() {
        return car;
    }
}
