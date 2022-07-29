package com.lishizhan.aliblog;

import com.lishizhan.aliblog.common.RegexUtils;
import com.lishizhan.aliblog.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class AliBlogServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test01(){
        String s = "18666041927@qq.com";
        //判断是否为手机号登陆
        if (!RegexUtils.isPhoneInvalid(s)) {
            System.out.println("手机号 = " + s);
        }
        //判断是否为邮箱登陆
        if (!RegexUtils.isEmailInvalid(s)) {
            System.out.println("邮箱 = " + s);
        }

    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void pwd(){
        String encode = passwordEncoder.encode("lishizhan");
        System.out.println("encode = " + encode);

        boolean lishizhan = passwordEncoder.matches("lishizhan", encode);
        System.out.println("lishizhan = " + lishizhan);


    }




}
