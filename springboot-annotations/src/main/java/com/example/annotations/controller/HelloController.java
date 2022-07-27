package com.example.annotations.controller;

import com.example.annotations.beans.Person;
import com.example.annotations.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : Alishiz
 * @Date : 2022/7/6/0006
 * @email : 1575234570@qq.com
 * @Description :
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Map<String,Object> hello(){
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("code",0);
        hashMap.put("data",null);
        return hashMap;
    }

    @Autowired
    private Person person;

    @GetMapping("info")
    public Person userInfo(){

        return person;
    }


}
