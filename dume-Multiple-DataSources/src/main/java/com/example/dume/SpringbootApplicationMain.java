package com.example.dume;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author dume
 * @create 2021-12-24 11:28
 **/
//不使用DruidDataSourceAutoConfigure默认配置，使用自定义配置
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
public class SpringbootApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplicationMain.class, args);
    }

}
