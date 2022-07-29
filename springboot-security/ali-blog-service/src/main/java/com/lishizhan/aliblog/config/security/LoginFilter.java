package com.lishizhan.aliblog.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lishizhan.aliblog.exception.CodeErrException;
import lombok.Data;
import lombok.SneakyThrows;
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

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //1，判断是否是post
        if (!request.getMethod().equalsIgnoreCase("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //2，判断是否是json
        if (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
            //3，从json中获取用输入的手机号或邮箱和密码和验证码
            Map<String, String> userInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            String phone = userInfo.get(getPhoneParameter());
            String email = userInfo.get(getEmailParameter());
            String password = userInfo.get(getPasswordParameter());
            String code = userInfo.get(getCodeParameter());
            if (code == null) throw new CodeErrException("验证码不能为空");
            //获取session
            String sessionCode = (String) request.getSession().getAttribute(getCodeParameter());
            //用户输入的验证码与session中存储的验证码进行比较
            if (!ObjectUtils.isEmpty(code) && !ObjectUtils.isEmpty(sessionCode) && code.equalsIgnoreCase(sessionCode)) {
                String username = null;
                if (!ObjectUtils.isEmpty(phone)) username = phone;
                if (!ObjectUtils.isEmpty(email)) username = email;
                log.info("LoginFilter-->attemptAuthentication--->用户：{}，手机号：{}，邮箱：{}，密码：{}，验证吗：{}", username, phone, email, password, code);
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
                setDetails(request, token);
                return this.getAuthenticationManager().authenticate(token);
            }
            //图形验证码不匹配异常
            throw new CodeErrException("验证码错误");
        }
        //如果用户提交的不是json数据那么就交给父类去处理
        return super.attemptAuthentication(request, response);
    }
}
