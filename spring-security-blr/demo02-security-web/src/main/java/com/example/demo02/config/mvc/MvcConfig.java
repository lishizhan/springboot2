package com.example.demo02.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author : lishizhan
 * @Date : 2022/8/13/0013
 * @email : 1575234570@qq.com
 * @Description :
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    /**
     * 要是进行页面跳转就不需要提供控制器了
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/tips").setViewName("tips");
        //WebMvcConfigurer.super.addViewControllers(registry);
    }

}
