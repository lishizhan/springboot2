package com.example.springbooteasypoi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : lishizhan
 * @Date : 2022/8/21/0021
 * @email : 1575234570@qq.com
 * @Description :
 */
@Configuration
@MapperScan(basePackages = "com.example.springbooteasypoi.mapper")
public class MybatisPlusConfig {
}
