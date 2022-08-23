package com.example.multidatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author : lishizhan
 * @Date : 2022/8/22/0022
 * @email : 1575234570@qq.com
 * @Description :
 */
@SpringBootApplication
@EnableScheduling
public class MultiDataSourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultiDataSourceApplication.class,args);
    }
}
