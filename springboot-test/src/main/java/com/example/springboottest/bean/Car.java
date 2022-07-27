package com.example.springboottest.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author : Alishiz
 * @Date : 2022/6/17/0017
 * @email : 1575234570@qq.com
 * @Description :
 */
@Data
@ToString
@Component
@PropertySource(value = "classpath:car.properties")
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String brand;
    private Integer price;
}
