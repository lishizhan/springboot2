package com.lishizhan.aliblog.config.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : Alishiz
 * @Date : 2022/7/28/0028
 * @email : 1575234570@qq.com
 * @Description :
 */
@Configuration
@MapperScan(basePackages = "com.lishizhan.aliblog.mapper")
public class MybatisPlusConfig {


}
