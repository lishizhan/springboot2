package com.lishizhan.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author : Alishiz
 * @Date : 2022/8/2/0002
 * @email : 1575234570@qq.com
 * @Description :
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 设置跨域访问
     * */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET","POST","DELETE","PUT")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
