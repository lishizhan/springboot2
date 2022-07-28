package com.example.security05beforeafter.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author lishizhan
 * @version 1.0
 * @description: 自定义前后端分离认证
 * @date 2022/7/28 11:17
 */

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //1，判断是否是post
        if (!request.getMethod().equalsIgnoreCase("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //2，判断是否是json
        if (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)){
            //3，从json中获取用输入的用户名和密码
            try {
                Map<String,String> userInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                String username = userInfo.get(getUsernameParameter());
                String password = userInfo.get(getPasswordParameter());
                System.out.println("username = " + username);
                System.out.println("password = " + password);
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);
                setDetails(request,token);
                return this.getAuthenticationManager().authenticate(token);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //如果用户提交的不是json数据那么就交给父类去处理
        return super.attemptAuthentication(request, response);
    }
}
