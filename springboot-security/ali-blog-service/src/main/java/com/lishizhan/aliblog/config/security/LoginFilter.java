package com.lishizhan.aliblog.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;

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
@Slf4j
@Data
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    public static final String EMAIL_PARAMETER = "email";
    public static final String PHONE_PARAMETER = "phone";
    public static final String code_PARAMETER = "code";

    private String emailParameter = EMAIL_PARAMETER;
    private String phoneParameter = PHONE_PARAMETER;
    private String codeParameter = code_PARAMETER;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //1，判断是否是post
        if (!request.getMethod().equalsIgnoreCase("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //2，判断是否是json
        if (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
            //3，从json中获取用输入的手机号或邮箱和密码
            try {
                Map<String, String> userInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                String phone = userInfo.get(getPhoneParameter());
                String email = userInfo.get(getEmailParameter());
                String password = userInfo.get(getPasswordParameter());
                String code = userInfo.get(getCodeParameter());
                String username = null;
                if (!ObjectUtils.isEmpty(phone)) username = phone;
                if (!ObjectUtils.isEmpty(email)) username = email;
                log.info("LoginFilter-->attemptAuthentication--->用户：{}，手机号：{}，邮箱：{}，密码：{}", username, phone, email, password);
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
                setDetails(request, token);
                return this.getAuthenticationManager().authenticate(token);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //如果用户提交的不是json数据那么就交给父类去处理
        return super.attemptAuthentication(request, response);
    }
}
