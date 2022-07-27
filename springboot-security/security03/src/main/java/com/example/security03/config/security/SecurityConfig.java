package com.example.security03.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/7/27 11:36
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/index/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/index/login.html")
                .loginProcessingUrl("/login") //登录请求处理页面
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(new SuccessAuthenticationHandler())
                .failureHandler(new FailureAuthenticationHandler())
                .and()
                .csrf().disable();
    }

    /**
     * 放行静态资源
     * */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers(
                "/static/**"
        );
    }


   /* @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager userDetailsService= new InMemoryUserDetailsManager();
        userDetailsService.createUser(User.withUsername("zhans").password("{noop}123123").roles("admin").build());
        return userDetailsService;
    }*/
    /**
     * 注入自定义操作数据的UserDetailsService
     * */
    @Autowired
    private UserDetailDbService userDetailDbService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService());
        auth.userDetailsService(userDetailDbService);
    }
}
