package com.example.security05beforeafter.config.security;

import com.example.security05beforeafter.service.impl.UserServiceImpl;
import com.example.security05beforeafter.vo.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author : Alishiz
 * @Date : 2022/7/27/0027
 * @email : 1575234570@qq.com
 * @Description : Spring security 的相关配置
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 自定义的loginFilter交给Spring管理
     */
    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        //设置接受json的用户名和密码
        loginFilter.setUsernameParameter("username");
        loginFilter.setPasswordParameter("password");
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated() //任何请求都认证
                .and()
                .formLogin();
        //at 对某个过滤器链中的过滤器进行替换，before/alter:表示放在那个过滤器之前或之后
        //这样写就实现了替换之前的表单认证过滤器
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);

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
//                    response.setContentType("application/json");
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);//"application/json"
                    PrintWriter writer = response.getWriter();
                    writer.write(new ObjectMapper().writeValueAsString(R.error(HttpStatus.UNAUTHORIZED.value(), "未登陆请登录")));
                    writer.flush();
                    writer.close();

                });
        //退出登录
        http.logout()
                .logoutUrl("/logout")
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

    /*@Override
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
                .defaultSuccessUrl("/index.html",true)
                .failureUrl("/login.html") //登录失败重定向到登录页面并显示错误信息，信息是放在session作用域中
                .and()
                .logout() //开启退出登陆
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html") //退出成功跳转登陆界面
                .and()
                .csrf().disable();
    }
*/
    /*@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/static/**");
    }*/


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
     */
    //注入自己实现的UserDetailService
    @Autowired
    private UserServiceImpl userDetailsService;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
