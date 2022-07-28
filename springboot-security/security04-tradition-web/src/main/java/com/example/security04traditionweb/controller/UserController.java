package com.example.security04traditionweb.controller;

import com.example.security04traditionweb.vo.R;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.http.SecurityHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Alishiz
 * @Date : 2022/7/27/0027
 * @email : 1575234570@qq.com
 * @Description :
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/user")
    public R user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return R.ok().put("authentication", authentication);

    }
}
