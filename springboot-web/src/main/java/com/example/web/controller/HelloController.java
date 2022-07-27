package com.example.web.controller;

import com.example.web.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;


    @GetMapping("bug.png")
    public String test(){
        return "bug";
    }


    @GetMapping("/hello")
    public List<String> hello(){
        //klfgnalkfg
        List<String> users = helloService.users();

        return users;

    }




}
