package com.example.demo02.controller;


import com.example.demo02.entity.dto.UserDto;
import com.example.demo02.service.UserService;
import com.example.demo02.vo.ResultVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private UserService userService;

    @GetMapping("info")
    public ResultVo info(){
        log.info("系统受限资源访问成功！！！");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("获取用户登陆信息--->{}",authentication.getPrincipal());
        UserDto userDto = (UserDto) authentication.getPrincipal();
        return ResultVo.ok().put("data", authentication).put("Principal",userDto);
    }
    @GetMapping("/getUserById/{id}")
    public ResultVo getUserById(@PathVariable("id") Long id){
        return ResultVo.ok(userService.getById(id));
    }


}
