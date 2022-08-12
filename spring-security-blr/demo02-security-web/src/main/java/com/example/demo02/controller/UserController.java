package com.example.demo02.controller;


import com.example.demo02.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : lishizhan
 * @Date : 2022/8/11/0011
 * @email : 1575234570@qq.com
 * @Description : 受限资源
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @GetMapping("info")
    public ResultVo info(){
        log.info("系统受限资源访问成功！！！");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResultVo.ok().put("data",authentication.getPrincipal());
    }

}
