package com.example.security07.config.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.security07.common.RedisConstants;
import com.example.security07.dto.UserDto;
import com.example.security07.exception.TokenAuthenticationFailureException;
import com.example.security07.utils.JWTUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Author : Alishiz
 * @Date : 2022/7/30/0030
 * @email : 1575234570@qq.com
 * @Description :JWT授权过滤器
 * OncePerRequestFilter 继承这个类那么每个请求就只过滤一次
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            //没有token,直接放行
            filterChain.doFilter(request, response);
            //如果不放行那么登陆接口也会被拦截
            return;
        }
        //解析token
        try {
            //认证token是否合法
            DecodedJWT verify = JWTUtil.verify(token);
            //从token中获取用户的id
            String id = verify.getClaims().get("id").asString();
            //从redis中获取用户信息
            String s = stringRedisTemplate.opsForValue().get(RedisConstants.LOGIN_USER_KEY + id);
            if (s==null) throw new TokenAuthenticationFailureException("用户未登录");
            //拿取用户信息
            UserDto userDto = JSON.parseObject(s, new TypeReference<UserDto>() {
            });
            //存入SecurityContextHolder
            //TODO 获取权限信息封装：authorities
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDto, null, null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            //放行
            filterChain.doFilter(request,response);

        } catch (Exception e) {
            logger.info("JWT认证失败", e);
            throw new TokenAuthenticationFailureException("token非法");
        }
    }
}
