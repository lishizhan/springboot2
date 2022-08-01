package com.example.security07.config.security;

import com.example.security07.config.security.filter.JwtAuthenticationTokenFilter;
import com.example.security07.vo.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author : Alishiz
 * @Date : 2022/7/29/0029
 * @email : 1575234570@qq.com
 * @Description :
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 自定token过滤器
     */
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    /**
     * 更换密码加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf
        http.csrf().disable()
                //不通过session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //对于登陆接口运行匿名访问
                .antMatchers("/login").anonymous()
                //除以上接口外所有的接口都要认证
                .anyRequest().authenticated();

        //添加token 过滤
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //退出登陆


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
    }
}
