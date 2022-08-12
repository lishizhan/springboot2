package com.example.demo01.controller;

import com.example.demo01.vo.ResultVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/8/11 17:17
 */
@RestController
@RequestMapping("auth")
public class LoginController {

    @PostMapping("login")
    public ResultVo login(){
        return ResultVo.ok("登陆成功");
    }

}
