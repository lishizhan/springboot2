package com.example.springbootjwt.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.springbootjwt.utils.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[JwtInterceptor] [preHandle] 拦截器执行");
        Map<String, Object> res = new HashMap<>();
        //获取请求头中的token
        String token = request.getHeader("token");
        //验证令牌是否准确
        try {
            JWTUtil.verify(token);
            //验证正确放行请求
            return true;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            res.put("msg", "签名不⼀致");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            res.put("msg", "令牌过期");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            res.put("msg", "算法不匹配");
        } catch (InvalidClaimException e) {
            e.printStackTrace();
            res.put("msg", "失效的payload");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("msg", "token⽆效");
        }
        res.put("code", 100);
        //将map转为json响应给用户
        String json = new ObjectMapper().writeValueAsString(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(json);
        return false;
    }

}
