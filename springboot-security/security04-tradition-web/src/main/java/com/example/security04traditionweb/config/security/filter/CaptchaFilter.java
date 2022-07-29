package com.example.security04traditionweb.config.security.filter;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lishizhan
 * @version 1.0
 * @description: 自定义验证码过滤
 * @date 2022/7/28 17:05
 */
@Data
public class CaptchaFilter extends UsernamePasswordAuthenticationFilter {
    public static final String CAPTCHA_KEY = "code";
    private String captcha = CAPTCHA_KEY;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //判断请求的是否为post
        if (!request.getMethod().equalsIgnoreCase("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //1，从请求中获取验证码
        String code = request.getParameter(getCaptcha());
        //2，从session中拿验证码进行比较
        String sessionCode = (String) request.getSession().getAttribute("captcha");

        if (!ObjectUtils.isEmpty(code) && !ObjectUtils.isEmpty(sessionCode) && sessionCode.equalsIgnoreCase(code)) {
            return super.attemptAuthentication(request, response);
        }
        throw new CredentialsExpiredException("验证码错误");

    }
}
