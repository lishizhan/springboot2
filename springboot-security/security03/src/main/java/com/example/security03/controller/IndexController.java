package com.example.security03.controller;

import com.example.security03.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/7/27 15:20
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @GetMapping("hello")
    @ResponseBody
    public R hello() {
        return R.ok("Hello");
    }

    @GetMapping("login.html")
    public String login() {
        return "login";
    }




}
