package com.example.demo03.config.security;

import com.example.demo03.vo.ResultVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.web.filter.CompositeFilter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/8/12 16:09
 */
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /*使用数据库配置数据源*/
    @Autowired
    private MyUserDetailService myUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * 登陆认证的过滤器
     *
     * @return
     */
    @Bean
    public AuthLoginFilter authLoginFilter() throws Exception {
        AuthLoginFilter authLoginFilter = new AuthLoginFilter();
        //指定URL
        authLoginFilter.setFilterProcessesUrl("/login");
        //设置获取参数
        authLoginFilter.setUsernameParameter("uname");
        authLoginFilter.setPasswordParameter("pwd");
        authLoginFilter.setAuthenticationManager(authenticationManager());

        //设置登录成功后，进行json响应;
        authLoginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            writer.write(new ObjectMapper()
                    .writeValueAsString(ResultVo.ok("登录成功").put("data", authentication)));
            writer.flush();
            writer.close();
        });
        authLoginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            ResultVo resultVo = ResultVo.error("登录失败").put("data", exception.getMessage());
            writer.write(new ObjectMapper().writeValueAsString(resultVo));
            writer.flush();
            writer.close();
        });

        return authLoginFilter;
    }

    //动态权限认证
    @Resource
    private CustomSecurityMetadataSource customSecurityMetadataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        //请求放行
        http.authorizeRequests()
                .mvcMatchers("/index/**").permitAll()
                .antMatchers("/captcha").permitAll()
                //.antMatchers("/admin/**").hasRole("admin") //具备admin权限
                //.mvcMatchers("/myLoginPage").permitAll()
                .anyRequest().authenticated();
        //用户登陆
        http.formLogin();





        //开启记住我
        //http.rememberMe();

        //用户注销
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    PrintWriter writer = response.getWriter();
                    ResultVo resultVo = ResultVo.ok("注销成功");
                    writer.write(new ObjectMapper().writeValueAsString(resultVo));
                    writer.flush();
                    writer.close();
                });

        //异常处理，访问没有认证的资源时就不会跳转登陆界面了
        http.exceptionHandling()
                .authenticationEntryPoint((request, response, e) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    PrintWriter writer = response.getWriter();
                    ResultVo resultVo = ResultVo.error(HttpStatus.UNAUTHORIZED.value(), "未认证，请登录！").put("data", e.getMessage());
                    writer.write(new ObjectMapper().writeValueAsString(resultVo));
                    writer.flush();
                    writer.close();
                })
                .accessDeniedHandler((request, response, e) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    PrintWriter writer = response.getWriter();
                    ResultVo resultVo = ResultVo.error(HttpStatus.UNAUTHORIZED.value(), "无权限访问！！").put("data", e.getMessage());
                    writer.write(new ObjectMapper().writeValueAsString(resultVo));
                    writer.flush();
                    writer.close();
                })
        ;

        //登陆字段的验证，对UsernamePasswordAuthenticationFilter进行替换，认证操作就在自定义的authLoginFilter进行
        http.addFilterAt(authLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();

        //会话处理
        http.sessionManagement()
                .maximumSessions(1)
                .expiredSessionStrategy(event -> {
                    HttpServletResponse response = event.getResponse();
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    PrintWriter writer = response.getWriter();
                    ResultVo resultVo = ResultVo.error(HttpStatus.UNAUTHORIZED.value(), "当前账号已在其他地方登陆！");
                    writer.write(new ObjectMapper().writeValueAsString(resultVo));
                    writer.flush();
                    writer.close();
                })
                .sessionRegistry(sessionRegistry()) //会话共享，交给redis
                .maxSessionsPreventsLogin(true); //一旦登陆禁止再次登陆;
    }


    /**
     * 将session交给redis,
     */
    @Resource
    private FindByIndexNameSessionRepository findByIndexNameSessionRepository;


    @Bean
    public SpringSessionBackedSessionRegistry sessionRegistry() {
        return new SpringSessionBackedSessionRegistry(findByIndexNameSessionRepository);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //静态资源放行
        web.ignoring().mvcMatchers("/static/**");
    }

}
