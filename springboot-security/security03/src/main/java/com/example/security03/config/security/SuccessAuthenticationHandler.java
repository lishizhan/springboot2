package com.example.security03.config.security;


import com.example.security03.vo.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 前后端分离：自定义认证成功后的处理
 * */

public class SuccessAuthenticationHandler implements AuthenticationSuccessHandler {

    /**认证成功返回json*/
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        R r = R.ok().put("data", authentication);
        String json = new ObjectMapper().writeValueAsString(r);
        response.getWriter().print(json);
    }

}
