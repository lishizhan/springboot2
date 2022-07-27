package com.example.mybatisplus.controller;

import com.example.mybatisplus.entity.User;
import com.example.mybatisplus.service.UserService;
import com.example.mybatisplus.service.impl.UserServiceImpl;
import com.example.mybatisplus.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    private UserService userService;



    @GetMapping
    public String index() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.selectAll();
        log.info("users---->{}",users);
        modelAndView.addObject("users",users);

        return "index";
    }


    @PostMapping("user")
    @ResponseBody
    public R user(User user) {
        log.info("user--->{}", user);

        return userService.insert(user) == 1 ? R.ok() : R.error(100, "插入用户失败");

    }




}
