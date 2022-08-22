package com.example.demo03.controller;

import com.example.demo03.entity.dto.UserDto;
import com.example.demo03.vo.ResultVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : lishizhan
 * @Date : 2022/8/14/0014
 * @email : 1575234570@qq.com
 * @Description :
 */
@RestController
@RequestMapping("/admin")
public class AuthController {

    @GetMapping("/delete")
    public ResultVo admin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = (UserDto) authentication.getPrincipal();
        return ResultVo.ok("admin 权限访问！",user);
    }

}
