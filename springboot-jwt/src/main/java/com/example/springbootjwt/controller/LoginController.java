package com.example.springbootjwt.controller;

import com.example.springbootjwt.pojo.User;
import com.example.springbootjwt.service.UserService;
import com.example.springbootjwt.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public Map<String, Object> userLogin(User user) {
        Map<String, Object> res = new HashMap<>();
        log.info("user登录信息----->用户名：{}，密码：{}", user.getName(), user.getPwd());
        try {
            User login = userService.login(user);

            //生成JWT
            Map<String,String> map = new HashMap<>();
            map.put("name",login.getName());
            map.put("id",login.getId().toString());
            String token = JWTUtil.getToken(map);

            res.put("msg","LOGIN_SUCCESS");
            res.put("code",0);
            res.put("token",token);
        } catch (Exception e) {
            res.put("msg","ERROR");
            res.put("code",0);
            throw new RuntimeException(e);
        }
        return res;
    }
}
