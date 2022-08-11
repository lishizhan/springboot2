package com.example.stream01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/8/8 11:29
 */
@RestController
public class HelloController {


    @GetMapping("")
    public String hello(){
        Map<String,Object> he = new HashMap<>();
        Set<String> String = new HashSet<>();
        return "";
    }


}
