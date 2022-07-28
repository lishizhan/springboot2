package com.example.security04traditionweb.config.security;

import com.example.security04traditionweb.config.security.filter.CaptchaFilter;
import com.example.security04traditionweb.service.UserService;
import com.example.security04traditionweb.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author : Alishiz
 * @Date : 2022/7/27/0027
 * @email : 1575234570@qq.com
 * @Description : Spring security 的相关配置
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CaptchaFilter captchaFilter() throws Exception {
        CaptchaFilter captchaFilter = new CaptchaFilter();
        captchaFilter.setFilterProcessesUrl("/doLogin");
        captchaFilter.setUsernameParameter("username");
        captchaFilter.setPasswordParameter("password");
        captchaFilter.setCaptcha("code");
        //指定认证管理器
        captchaFilter.setAuthenticationManager(authenticationManagerBean());

        captchaFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.sendRedirect("/index.html");
        });
        captchaFilter.setAuthenticationFailureHandler((request, response, e) -> {
            response.sendRedirect("/login.html");
        });


        return captchaFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/login.html").permitAll() //放行某个请求
                .anyRequest().authenticated()  //任何请求都拦截
                .and()
                .formLogin()
                .loginPage("/login.html") //指定自定义登陆界面
                //.loginProcessingUrl("/doLogin") //登陆表单提交的请求地址
                //.usernameParameter("username") //input表单的name值
                //.passwordParameter("password")
                //登陆成功或失败的返回路径
                //.successForwardUrl("")
                //.failureForwardUrl("")
                //.defaultSuccessUrl("/index.html",true)
                //.failureUrl("/login.html") //登录失败重定向到登录页面并显示错误信息，信息是放在session作用域中
                .and()
                .logout() //开启退出登陆
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html") //退出成功跳转登陆界面
                .and()
                .csrf().disable();
        //at 对某个过滤器链中的过滤器进行替换，before/alter:表示放在那个过滤器之前或之后
        //这样写就实现了替换之前的表单认证过滤器
        http.addFilterAt(captchaFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/static/**","/captcha");
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
    //注入自己实现的UserDetailService
    @Autowired
    private UserServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
