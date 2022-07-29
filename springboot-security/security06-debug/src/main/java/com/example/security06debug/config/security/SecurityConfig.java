package com.example.security06debug.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/7/29 17:39
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated() //任何请求都认证
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }
}
