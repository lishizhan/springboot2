package com.example.security03.config.security;


import com.example.security03.vo.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 前后端分离：自定义认证失败后的处理
 * */

public class FailureAuthenticationHandler implements AuthenticationFailureHandler {

    /**认证失败返回json*/
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        R r = R.error("认证失败").put("error",exception.getMessage() );
        String json = new ObjectMapper().writeValueAsString(r);
        response.getWriter().print(json);
    }
}
