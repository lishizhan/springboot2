package com.example.demo01.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/8/11 15:23
 * 公共资源
 */
@Slf4j
@RestController
@RequestMapping("index")
public class IndexController {
    @GetMapping("hello")
    public String hello(){
        log.info("系统开放资源访问成功！");
        return "Hello Security!!!";
    }
}
