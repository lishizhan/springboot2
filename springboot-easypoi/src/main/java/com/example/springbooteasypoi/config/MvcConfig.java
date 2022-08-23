package com.example.springbooteasypoi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author : lishizhan
 * @Date : 2022/8/21/0021
 * @email : 1575234570@qq.com
 * @Description :
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {


 /*   @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("home").setViewName("index");
    }*/
}
