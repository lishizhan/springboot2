package com.example.annotations.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author : Alishiz
 * @Date : 2022/7/7/0007
 * @email : 1575234570@qq.com
 * @Description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Component
@ConfigurationProperties(prefix = "myinfo")
public class Person {
    private String name;
    private Integer age;


}
