package com.example.security01.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author : Alishiz
 * @Date : 2022/7/25/0025
 * @email : 1575234570@qq.com
 * @Description :
 */
/*
 * 思考：
 *      1,为什么引入Security后没有任何配置需要认证
 *      2,在项目中任何登陆界面，登陆界面怎么来的
 *      3,为什么使用user和控制台的密码登陆，登陆时的数据源在哪里
 * */


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .mvcMatchers("/index/**").permitAll() //资源放行
               .anyRequest().authenticated() //除了/index/hello的都需要认证
               .and()
               .formLogin() //用表单验证
               .loginPage("/index/login.html")//更换自定义登录页面
               .loginProcessingUrl("/login") //登录请求处理页面
               .usernameParameter("phone_or_email")
               .passwordParameter("password")
               //.successForwardUrl("") //认证成功的跳转路径 forward 注意不会跳转到之前请求的路径
               .defaultSuccessUrl("/index/success") //默认认证成功的跳转 redirect 如果之前有请求路径，那么认证后就是之前的，如果一来就是登录那么登录成功后就直接跳转默认
               .and()
               .csrf().disable();//禁止csrf 跨站请求保护
    }

/*    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers(
                "/**"
        );

    }*/
}
