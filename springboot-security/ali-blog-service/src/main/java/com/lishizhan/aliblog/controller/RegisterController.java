package com.lishizhan.aliblog.controller;

import com.lishizhan.aliblog.common.R;
import com.lishizhan.aliblog.dto.UserRegisterDto;
import com.lishizhan.aliblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/7/29 11:59
 */
@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public R register(@RequestBody @Valid UserRegisterDto userRegisterDto){
        return userService.register(userRegisterDto);
    }


}
