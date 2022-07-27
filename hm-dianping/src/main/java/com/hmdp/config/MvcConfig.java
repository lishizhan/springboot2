package com.hmdp.config;

import com.hmdp.intercepter.LoginInterceptor;
import com.hmdp.intercepter.ReflushInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author : Alishiz
 * @Date : 2022/7/17/0017
 * @email : 1575234570@qq.com
 * @Description :
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                //不需要拦截的接口
                .excludePathPatterns(
                        "/user/code",
                        "/user/login",
                        "/blog/hot",
                        "/shop/**",
                        "/shop-type/**",
                        "/upload/**",
                        "voucher/**").order(2);
        /*
        * 该拦截器先执行获取用户信息，将order设置为最小值
        * */
        registry.addInterceptor(new ReflushInterceptor(redisTemplate)).addPathPatterns("/**").order(0);
    }
}
