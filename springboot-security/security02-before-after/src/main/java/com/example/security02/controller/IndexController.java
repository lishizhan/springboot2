package com.example.security02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("index")
public class IndexController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "index";
    }

    @GetMapping("success")
    @ResponseBody
    public String success(){
        return "SUCCESS";
    }

    @GetMapping("/login.html")
    public String login(){
        return "login";
    }




}
