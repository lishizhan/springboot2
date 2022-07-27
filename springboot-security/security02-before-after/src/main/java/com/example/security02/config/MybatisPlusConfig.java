package com.example.security02.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : Alishiz
 * @Date : 2022/7/25/0025
 * @email : 1575234570@qq.com
 * @Description :
 */
@Configuration
@MapperScan(basePackages = "com.example.security02.dao")
public class MybatisPlusConfig {
}
