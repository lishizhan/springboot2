package com.example.demo02.config.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : lishizhan
 * @Date : 2022/8/13/0013
 * @email : 1575234570@qq.com
 * @Description :
 */
@Configuration
@MapperScan(basePackages = "com.example.demo02.mapper")
public class MyMyBatisPlusConfig {
}
