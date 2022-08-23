package com.example.multidatasource.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : lishizhan
 * @Date : 2022/8/22/0022
 * @email : 1575234570@qq.com
 * @Description :
 */
@Configuration
@MapperScan(basePackages = "com.example.multidatasource.mapper")
public class MybatisplusConfig {
}
