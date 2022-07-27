package com.example.springbootredis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.example.springbootredis.mapper")
public class MybatisPlusConfig {
}
