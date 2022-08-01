package com.example.security07.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.security07.entity.User;
import com.example.security07.service.UserService;
import com.example.security07.vo.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Alishiz
 * @Date : 2022/7/29/0029
 * @email : 1575234570@qq.com
 * @Description :
 */
@RestController
public class HelloController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    @GetMapping("/hello/{username}")
    public R hello(@PathVariable(value = "username",required = true) String username){
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        String s = JSON.toJSONString(user);
        stringRedisTemplate.opsForValue().set("security07:hello",s);
        return R.ok("SUCCESS").put("data",user);
    }

}
