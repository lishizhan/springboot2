package com.example.demo02;

import com.example.demo02.entity.Role;
import com.example.demo02.service.RoleService;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class Demo02SecurityWebApplicationTests {

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    /*@Test
    public void test01(){
        String pwd = "lishizhan";
        String encode = passwordEncoder.encode(pwd);
        System.out.println("encode = " + encode);
    }*/
    @Autowired
    private RoleService roleService;
    @Test
    public void test123(){
        List<Role> roles = roleService.getUserRolesByUserId(3L);
        roles.forEach(role -> {
            System.out.println("role = " + role);
        });
    }

}
