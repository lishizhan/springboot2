package com.example.security05beforeafter.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author : Alishiz
 * @Date : 2022/7/27/0027
 * @email : 1575234570@qq.com
 * @Description : 对spring MVC 进行自定义配置
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 这样就不需要给每个页面提供控制器进行页面跳转了
     * */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /*
        * @GetMapping("/login.html")
        * public String login(){
        *   return "login";
        * }
        * */
//        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/index").setViewName("index");
//        registry.addViewController("/index.html").setViewName("index");
//        registry.addViewController("/login.html").setViewName("login");

    }
}
