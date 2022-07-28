package com.example.springboottest.config;

import com.example.springboottest.bean.Person;
import com.example.springboottest.bean.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : Alishiz
 * @Date : 2022/6/8/0008
 * @email : 1575234570@qq.com
 * @Description :
 */
@Configuration(proxyBeanMethods = true)
public class FullAndLifeConfiguration {

    @Bean
    public Person person01(){
        return new Person("zhans",22);
    }

    @Bean
    public Pet pet01(){
        return new Pet("pig");
    }
}
