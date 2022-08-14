package com.example.demo03.config.security;

import com.example.demo03.common.RedisConstants;
import com.example.demo03.config.security.exception.CaptchaException;
import com.example.demo03.entity.User;
import com.example.demo03.entity.dto.UserDto;
import com.example.demo03.utils.RedisCache;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author : lishizhan
 * @Date : 2022/8/13/0013
 * @email : 1575234570@qq.com
 * @Description : 自定义前后端分离认证
 */

public class AuthLoginFilter extends UsernamePasswordAuthenticationFilter {

    public static final String CAPTCHA_CODE = "captcha";
    @Getter
    @Setter
    private String captchaParameter = CAPTCHA_CODE;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登陆请求过来进行判断
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //1，判断是否为post
        if (!request.getMethod().equalsIgnoreCase("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //2，判断是否为json格式
        if (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
            //3，从json数据中获取用户名和密码
            //将请求体中的数据转为map
            try {
                //获取请求数据
                Map<String, String> loginUserParams = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                String username = loginUserParams.get(this.getUsernameParameter());
                String password = loginUserParams.get(this.getPasswordParameter());
                String captcha = loginUserParams.get(this.getCaptchaParameter());
                if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) throw new CaptchaException("用户名或密码不能为空");
                if (StringUtils.isEmpty(captcha)) throw new CaptchaException("验证码不能为空");
                //从redis中获取验证码
                String captchaRedis = redisCache.getCacheObject(RedisConstants.CAPTCHA_KEY + request.getRemoteAddr());
                //判断验证码的合法性
                if (ObjectUtils.isEmpty(captchaRedis)) throw new CaptchaException("验证码已经过期");
                if (!captcha.equalsIgnoreCase(captchaRedis)) throw new CaptchaException("验证码错误");

                //验证码验证通过
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
                setDetails(request, authenticationToken);
                return this.getAuthenticationManager().authenticate(authenticationToken);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //如果用户提交的不是json数据那么就交给父类去处理
        return super.attemptAuthentication(request, response);
    }
}
