package com.example.security02.config.security;

import com.example.security02.dto.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 注销成功的json处理
 * */
public class SuccessLogoutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        R r = R.ok("注销成功").put("data", authentication);
        String json = new ObjectMapper().writeValueAsString(r);
        response.getWriter().print(json);
    }
}
