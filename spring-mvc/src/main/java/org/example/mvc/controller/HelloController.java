package org.example.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author : Alishiz
 * @Date : 2022/7/12/0012
 * @email : 1575234570@qq.com
 * @Description :
 */
@Controller
public class HelloController {

    @GetMapping("/hello.do")
    @ResponseBody
    public String hello(){return "Hello Spring MVC";}

}
