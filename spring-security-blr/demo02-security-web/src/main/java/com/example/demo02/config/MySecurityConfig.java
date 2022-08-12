package com.example.demo02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/8/12 16:09
 */
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/index/**").permitAll()
                .mvcMatchers("/myLoginPage").permitAll()
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/myLoginPage")
                .usernameParameter("uname")
                .passwordParameter("pwd")
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/index", true)
                //登录成功后不进行页面跳转，进行json响应
                /*.successHandler((request, response, authentication) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    PrintWriter writer = response.getWriter();
                    writer.write(new ObjectMapper()
                            .writeValueAsString(ResultVo.ok("登录成功").put("data",authentication)));
                    writer.flush();
                    writer.close();
                })
                .failureHandler((request, response, exception) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    PrintWriter writer = response.getWriter();
                    ResultVo resultVo = ResultVo.error("登录失败").put("data", exception.getMessage());
                    writer.write(new ObjectMapper().writeValueAsString(resultVo));
                    writer.flush();
                    writer.close();
                });*/
                .and()
                .logout()
                .logoutUrl("/logout")
                /*.logoutRequestMatcher(new OrRequestMatcher(
                //配置多个退出登录
                        new AntPathRequestMatcher("/logout1","GET"),
                        new AntPathRequestMatcher("logout2","POST")
                ))*/
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/myLoginPage");
                /*.logoutSuccessHandler((request, response, authentication) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    PrintWriter writer = response.getWriter();
                    writer.write(new ObjectMapper()
                            .writeValueAsString(ResultVo.ok("注销成功").put("data",authentication)));
                    writer.flush();
                    writer.close();
                });*/
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/static/**");
        ///static/bootstrap/js/jquery.min.js
    }

    /*使用内存配置数据源*/
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(new User("zhans","{noop}123123",null));
        return userDetailsManager;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
}
