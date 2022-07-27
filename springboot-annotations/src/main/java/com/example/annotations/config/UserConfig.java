package com.example.annotations.config;

import com.example.annotations.beans.Person;
import com.example.annotations.beans.Pet;
import com.example.annotations.beans.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author : Alishiz
 * @Date : 2022/7/6/0006
 * @email : 1575234570@qq.com
 * @Description :
 */
@Configuration
@ImportResource("classpath:beans/beans.xml")
@EnableConfigurationProperties(Person.class)
public class UserConfig {

    @Bean
    @ConditionalOnMissingBean(name = "tom") //当组件中有tom才会注册user, 加在类上则所有的组件都会被注入
    public User user(){
        return new User("zhans",23);
    }

    @Bean("tom")
    public Pet pet(){
        return new Pet("Tom",1);
    }
}
