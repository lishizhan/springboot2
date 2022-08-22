package com.example.demo03.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : lishizhan
 * @Date : 2022/8/13/0013
 * @email : 1575234570@qq.com
 * @Description :
 */
@Configuration
@MapperScan(basePackages = "com.example.demo03.mapper")
public class MyMyBatisPlusConfig {
}
