package com.lishizhan.aliblog.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lishizhan.aliblog.common.R;
import com.lishizhan.aliblog.dto.UserDto;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        //???????????????????????????
        http.authorizeRequests().anyRequest().authenticated();
        //?????????????????????????????????
        http.formLogin().and().addFilterAt(loginFilter(),UsernamePasswordAuthenticationFilter.class);
        //????????????????????????????????????????????????
        http.exceptionHandling()
                //????????????
                .accessDeniedHandler((request, response, e) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    PrintWriter writer = response.getWriter();
                    writer.write(new ObjectMapper().writeValueAsString(R.error(HttpStatus.FORBIDDEN.value(), "??????????????????????????????")));
                    writer.flush();
                    writer.close();
                })
                //????????????
                .authenticationEntryPoint((request, response, e) -> {
                    response.setCharacterEncoding("UTF-8");
                    //response.setContentType("application/json");
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);//"application/json"
                    PrintWriter writer = response.getWriter();
                    writer.write(new ObjectMapper().writeValueAsString(R.error(HttpStatus.UNAUTHORIZED.value(), "??????????????????")));
                    writer.flush();
                    writer.close();

                });
        //????????????
        http.logout().logoutUrl("/logout")
                //????????????json??????
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    PrintWriter writer = response.getWriter();
                    writer.write(new ObjectMapper().writeValueAsString(R.ok("??????????????????")));
                    writer.flush();
                    writer.close();
                });
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/captcha","/register");
    }

    /**
     * ????????????loginFilter??????Spring??????
     */
    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        //????????????json?????????????????????
        loginFilter.setPhoneParameter("phone");
        loginFilter.setEmailParameter("email");
        loginFilter.setPasswordParameter("password");
        loginFilter.setCodeParameter("code");
        loginFilter.setFilterProcessesUrl("/doLogin");
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        //???????????????????????????
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.setContentType("application/json;charset=UTF-8");
            R r = R.ok().put("data", authentication);
            String json = new ObjectMapper().writeValueAsString(r);
            response.getWriter().print(json);
        });
        //???????????????????????????
        loginFilter.setAuthenticationFailureHandler((request, response, e) -> {
            response.setContentType("application/json;charset=UTF-8");
            R r = R.error("????????????").put("error", e.getMessage());
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

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
