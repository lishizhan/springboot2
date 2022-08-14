package com.example.demo04.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @Author : lishizhan
 * @Date : 2022/8/14/0014
 * @email : 1575234570@qq.com
 * @Description :
 */
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    /*使用基于内存校验*/
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager();
        detailsManager.createUser(User.withUsername("root").password("{noop}123123").roles("admin").build());
        detailsManager.createUser(User.withUsername("zhans").password("{noop}123123").roles("normal").build());
        return detailsManager;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .anyRequest().authenticated();
        http.formLogin();

        http.csrf().disable();
    }
}
