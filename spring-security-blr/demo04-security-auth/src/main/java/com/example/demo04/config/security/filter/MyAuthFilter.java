package com.example.demo04.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
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
 * @Author : lishizhan
 * @Date : 2022/8/14/0014
 * @email : 1575234570@qq.com
 * @Description :
 */
public class MyAuthFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        try {
            Map map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            String username = (String) map.get("username");
            String password = (String) map.get("password");
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username,password);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return super.attemptAuthentication(request, response);
    }
}
