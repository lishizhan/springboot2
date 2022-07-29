package com.example.security06debug.controller;

import com.example.security06debug.vo.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/7/29 17:40
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public R hello(){
        return R.ok("Hello");
    }
}
