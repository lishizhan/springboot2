package com.lishizhan.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/8/2 16:55
 */
@Configuration
@MapperScan(basePackages = "com.lishizhan.common.mapper")
public class MybatisPlusConfig {
}
