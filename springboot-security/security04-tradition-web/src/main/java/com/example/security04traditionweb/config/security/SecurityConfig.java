package com.example.security04traditionweb.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @Author : Alishiz
 * @Date : 2022/7/27/0027
 * @email : 1575234570@qq.com
 * @Description : Spring security 的相关配置
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/login.html").permitAll() //放行某个请求
                .anyRequest().authenticated()  //任何请求都拦截
                .and()
                .formLogin()
                .loginPage("/login.html") //指定自定义登陆界面
                .loginProcessingUrl("/doLogin") //登陆表单提交的请求地址
                .usernameParameter("username") //input表单的name值
                .passwordParameter("password")
                //登陆成功或失败的返回路径
                //.successForwardUrl("")
                //.failureForwardUrl("")
                .defaultSuccessUrl("/index.html")
                .and()
                .logout() //开启退出登陆
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html") //退出成功跳转登陆界面
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/static/**");
    }


    /**
     * 替换security全局的内存登陆信息配置
     *
     * */
    /*@Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("zhans").password("{noop}123123").roles("root").build());
        return userDetailsManager;
    }*/
    /**
     * 指定数据库实现
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
}
