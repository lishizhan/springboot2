package com.example.demo01.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/8/11 17:12
 */
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/index/**").permitAll()
                .antMatchers("/myLoginPage").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()//表单认证
                .loginPage("/myLoginPage")//自定义登录界面
                .loginProcessingUrl("/doLogin") //一旦自定义了登录界面，那么就需要提供请求处理的URL交给Security认证
                .usernameParameter("uname")// 表单中的name属性
                .passwordParameter("pwd")
                //successForwardUrl与defaultSuccessUrl只能二选一
                //坑：successForwardUrl指的是登录成功后的请求转发地址，而表单登录使用的post，那么登录成功后进行请求转发时也是post请求转发到这个地址。由于一般登录成功后跳转的地址都是get请求，从而导致错误
                //.successForwardUrl("/index") //认证成功跳转路径 转发
                .defaultSuccessUrl("/index",true)//认证成功跳转路径 重定向
                //.failureUrl("/myLoginPage")
                .and()
                //禁止跨站请求保护
                .csrf().disable();
    }
}
