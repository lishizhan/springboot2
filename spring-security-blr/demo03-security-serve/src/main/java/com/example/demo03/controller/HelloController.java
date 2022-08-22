package com.example.demo03.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : lishizhan
 * @Date : 2022/8/14/0014
 * @email : 1575234570@qq.com
 * @Description : 测试权限
 */
@RestController
public class HelloController {
    /**
     *
     * @return
     */
    @GetMapping("/admin/hello")
    public String admin() {
        return "hello admin";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "hello user";
    }

    @GetMapping("/guest/hello")
    public String guest() {
        return "hello guest";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
