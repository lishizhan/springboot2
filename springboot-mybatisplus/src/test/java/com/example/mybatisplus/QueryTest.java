package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.dao.UserMapper;
import com.example.mybatisplus.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class QueryTest {

    @Autowired
    private UserMapper userMapper;

    /*
    * Wrapper ： 条件构造抽象类，最顶端父类
        AbstractWrapper ： 用于查询条件封装，生成 sql 的 where 条件
        QueryWrapper ： Entity 对象封装操作类，不是用lambda语法
        UpdateWrapper ： Update 条件封装，用于Entity对象更新操作
        AbstractLambdaWrapper ： Lambda 语法使用 Wrapper统一处理解析 lambda 获取 column。
        LambdaQueryWrapper ：看名称也能明白就是用于Lambda语法使用的查询Wrapper
        LambdaUpdateWrapper ： Lambda 更新封装Wrapper
    * */

    @Test
    public void queryWrapperTest(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //设置条件
        //ge（大于等于）、gt(大于)、le（小于等于）、lt（小于）、isNull、isNotNull eq(等于) ne不等于
        //ge 大于等于
//        queryWrapper.ge("age",90);

        //eq、ne 等于不等于
        queryWrapper.eq("name","zhans99");
        //sd

        //between查询年龄在59和70之间
        /*queryWrapper.between("age",40,50);*/
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(user -> {
            System.out.println("user = " + user);
        });
    }


    @Test
    public void LambdaUpdateTest(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAge,"40");
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        users.forEach(user -> System.out.println("user = " + user));
    }
}
