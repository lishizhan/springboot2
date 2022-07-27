package com.example.springbootjwt;

import com.example.springbootjwt.dao.UserMapper;
import com.example.springbootjwt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootJwtApplicationTests {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void selectUserTest(){
        List<User> users = userMapper.selectList(null);
        users.forEach(user -> System.out.println("user = " + user));
    }


}
