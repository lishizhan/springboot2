package com.example.security02.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AutoPopulatingList;

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
@Slf4j
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 使用配置文件的方式定义登录的用户名和密码
     * */
    /*@Autowired
    public void initialize(AuthenticationManagerBuilder builder) throws Exception{
        log.info("[SpringSecurityConfig] [initialize] builder默认配置={}",builder);
        InMemoryUserDetailsManager userDetailsService= new InMemoryUserDetailsManager();
        userDetailsService.createUser(User.withUsername("zhans").password("{noop}123123").roles("admin").build());
        builder.userDetailsService(userDetailsService);
    }*/

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager userDetailsService= new InMemoryUserDetailsManager();
        userDetailsService.createUser(User.withUsername("zhans").password("{noop}123123").roles("admin").build());
        return userDetailsService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
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
               //.failureForwardUrl("")
               //.failureUrl("")
               //.successForwardUrl("") //认证成功的跳转路径 forward 注意不会跳转到之前请求的路径
               //.defaultSuccessUrl("/index/success") //默认认证成功的跳转 redirect 如果之前有请求路径，那么认证后就是之前的，如果一来就是登录那么登录成功后就直接跳转默认
               //前后端分离不需要默认返回，直接返回字符串
               .successHandler(new SuccessAuthenticationHandler()) //自定义认证成功的返回JSON
               .failureHandler(new FailureAuthenticationHandler()) //自定义认证失败的返回JSON
               .and()
               .logout()
               .logoutUrl("/logout") //指定注销登录的URL,只能用户get请求
               .logoutRequestMatcher(new OrRequestMatcher(
                       new AntPathRequestMatcher("/logout1","GET"),
                       new AntPathRequestMatcher("/logout2","POST")
               ))
               .invalidateHttpSession(true) //默认会话失效
               .clearAuthentication(true)//默认清楚认证标记
               //.logoutSuccessUrl("/index/login.html") //注销登录跳转登录页面
               .logoutSuccessHandler(new SuccessLogoutHandler()) //注销成功后返回的JSON消息
               .and()
               .csrf().disable();//禁止csrf 跨站请求保护
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers(
                "/static/**"
        );

    }
}
