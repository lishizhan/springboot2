package com.example.demo02.config.security;

import com.example.demo02.vo.ResultVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.util.UUID;

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
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/myLoginPage");
        //开启记住我功能
        http.rememberMe()
                .rememberMeParameter("remember-me")
                //提高记住我功能的安全性，使用数据持久化，注意：记住我功能的表security中会自动创建
                .tokenRepository(jdbcTokenRepository());

        http.csrf().disable();

        //配置会话管理
        http.sessionManagement()
                .maximumSessions(1)//允许非法并发只有一个
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
                //httpSessionEventPublisher添加这个bean
                .maxSessionsPreventsLogin(true)//"登录之后禁⽌再次登录;
                .and().invalidSessionUrl("/tips");//非法过期处理
    }

    /**
     * 为什么要加这个 Bean 呢？因为在 Spring Security 中，它是通过监听 session 的销毁事件，来及时的清理 session 的记录。用户从不同的浏览器登录后，都会有对应的 session，当用户注销登录之后，session 就会失效，但是默认的失效是通过调用 StandardSession#invalidate 方法来实现的，这一个失效事件无法被 Spring 容器感知到，进而导致当用户注销登录之后，Spring Security 没有及时清理会话信息表，以为用户还在线，进而导致用户无法重新登录进来（小伙伴们可以自行尝试不添加上面的 Bean，然后让用户注销登录之后再重新登录）。
     *
     * @return
     */
    @Bean
    HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }


    /**
     * 提高记住我功能的安全性，使用数据持久化，注意：记住我功能的表security中会自动创建
     */
    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository jdbcTokenRepository() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        //是否启动是创建数据库
        repository.setCreateTableOnStartup(false);
        //指定数据源
        repository.setDataSource(dataSource);
        return repository;
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/static/**");
    }

    /*使用内存配置数据源*/
    /*@Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("lishizhan").password("{noop}123123").roles("admin").build());
        return userDetailsManager;
    }*/
    /*使用数据库配置数据源*/
    @Autowired
    private MyUserDetailService myUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }
}
