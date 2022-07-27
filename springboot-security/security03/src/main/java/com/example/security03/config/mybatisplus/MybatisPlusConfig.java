package com.example.security03.config.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/7/27 13:26
 */
@Configuration
@MapperScan(basePackages = "com.example.security03.mapper")
public class MybatisPlusConfig {

}


