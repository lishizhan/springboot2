package com.example.security03.controller;

import com.example.security03.entity.Role;
import com.example.security03.entity.User;
import com.example.security03.entity.UserRole;
import com.example.security03.service.RoleService;
import com.example.security03.service.UserRoleService;
import com.example.security03.service.UserService;
import com.example.security03.vo.R;
import com.example.security03.vo.RoleVo;
import com.example.security03.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;
import java.util.List;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/7/27 11:31
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;



    /**
     * 获取一个用户
     */
    @GetMapping("user/{id}")
    public R user(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return R.ok().put("data", user);
    }

    /**
     * 获取多个用户列表
     */
    @GetMapping("users")
    public R users() {
        List<User> users = userService.getUserList();
        return R.ok().put("data", users);
    }

    /**
     * 增加一个用户
     */
    @PostMapping("user")
    public R insert(@RequestBody UserVo userVo) {
        System.out.println("userVo = " + userVo);
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        boolean b = userService.save(user);
        return b ? R.ok() : R.error();
    }

    /**
     * 添加权限
     */
    @PostMapping("/role")
    public R role(@RequestBody RoleVo roleVo) {
        Role role = new Role();
        BeanUtils.copyProperties(roleVo,role);
        return roleService.save(role) ? R.ok() : R.error();
    }

    /**
     * 测试切换lishizhan-home
     * */
    @GetMapping("/test")
    public R test(){
        return R.ok("test 一下home分支");
    }

}
