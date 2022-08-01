package com.example.security07.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : Alishiz
 * @Date : 2022/7/29/0029
 * @email : 1575234570@qq.com
 * @Description :
 */
@Configuration
@MapperScan(basePackages = "com.example.security07.mapper")
public class MybatisPlusConfig {
}
