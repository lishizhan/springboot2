package com.example.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Alishiz
 * @Date : 2022/6/7/0007
 * @email : 1575234570@qq.com
 * @Description :
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "测试包扫面！！！";
    }

}
