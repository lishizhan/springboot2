package com.lishizhan.aliblog.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lishizhan.aliblog.common.R;
import com.lishizhan.aliblog.dto.UserDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author : Alishiz
 * @Date : 2022/7/28/0028
 * @email : 1575234570@qq.com
 * @Description :
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //所有的请求都要认证
        http.authorizeRequests().anyRequest().authenticated();
        //替换表单登陆认证过滤器
        http.formLogin().and().addFilterAt(loginFilter(),UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                //权限异常
                .accessDeniedHandler((request, response, e) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    PrintWriter writer = response.getWriter();
                    writer.write(new ObjectMapper().writeValueAsString(R.error(HttpStatus.FORBIDDEN.value(), "权限不足请联系管理员")));
                    writer.flush();
                    writer.close();
                })
                //认证异常
                .authenticationEntryPoint((request, response, e) -> {
                    response.setCharacterEncoding("UTF-8");
                    //response.setContentType("application/json");
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);//"application/json"
                    PrintWriter writer = response.getWriter();
                    writer.write(new ObjectMapper().writeValueAsString(R.error(HttpStatus.UNAUTHORIZED.value(), "未登陆请登录")));
                    writer.flush();
                    writer.close();

                });
        //退出登陆
        http.logout().logoutUrl("/logout")
                //退出成功json响应
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    PrintWriter writer = response.getWriter();
                    writer.write(new ObjectMapper().writeValueAsString(R.ok("退出登录成功")));
                    writer.flush();
                    writer.close();
                });
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/captcha");
    }

    /**
     * 自定义的loginFilter交给Spring管理
     */
    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        //设置接受json的用户名和密码
        loginFilter.setPhoneParameter("phone");
        loginFilter.setEmailParameter("email");
        loginFilter.setPasswordParameter("password");
        loginFilter.setCodeParameter("code");
        loginFilter.setFilterProcessesUrl("/doLogin");
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        //登录成功的认证处理
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.setContentType("application/json;charset=UTF-8");
            R r = R.ok().put("data", authentication);
            String json = new ObjectMapper().writeValueAsString(r);
            response.getWriter().print(json);
        });
        //登录失败的认证处理
        loginFilter.setAuthenticationFailureHandler((request, response, e) -> {
            response.setContentType("application/json;charset=UTF-8");
            R r = R.error("认证失败").put("error", e.getMessage());
            String json = new ObjectMapper().writeValueAsString(r);
            response.getWriter().print(json);
        });
        return loginFilter;
    }

/*    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("zhans").password("{noop}123123").roles("root").build());
        return userDetailsManager;
    }*/

    @Autowired
    private UserDtoService userDtoService;
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDtoService);
    }
}
