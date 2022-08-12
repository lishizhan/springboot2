package com.example.demo01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/8/12 15:20
 */
@Controller
public class PageController {

    @GetMapping("myLoginPage")
    public String myLoginPage(){
        return "login";
    }
    @GetMapping("index")
    public String index(){
        return "index";
    }


}
