package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mybatisplus.dao.UserMapper;
import com.example.mybatisplus.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
public class StreamTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void ForeachTest(){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(User::getAge,30);
        List<User> users = userMapper.selectList(queryWrapper);
        List<User> userList = users.stream().filter(user -> {
            return user.getAge() > 90;
        }).collect(Collectors.toList());
        System.out.println("userList = " + userList);
    }



}
