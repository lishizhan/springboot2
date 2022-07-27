package com.hmdp.intercepter;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import com.hmdp.utils.RedisConstants;
import com.hmdp.utils.UserHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author : Alishiz
 * @Date : 2022/7/17/0017
 * @email : 1575234570@qq.com
 * @Description : 实现拦截器，拦截全部
 */
public class ReflushInterceptor implements HandlerInterceptor {

    /**
     * 注意这里要采用构造注入的方式，应为此类不是Spring管理的所以不能使用自动注入
     * */
    private StringRedisTemplate redisTemplate;

    public ReflushInterceptor(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1，获取请求头中的token
        String token = request.getHeader("authorization");
        //判断token是否为空
        if (StrUtil.isBlank(token)) {
            return true;
        }
        //2，基于token从redis中获取user
        Map<Object, Object> userMap = redisTemplate.opsForHash().entries(RedisConstants.LOGIN_USER_KEY + token);
        //3，判断用户是否存在
        if (userMap.isEmpty()) {
            //4，不存在拦截
            return true;
        }
        //将查询到的hash转为UserDTO
        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);
        //5，存在，保存信息到ThreadLocal
        UserHolder.saveUser(userDTO);

        //刷新token的有效期
        redisTemplate.expire(RedisConstants.LOGIN_USER_KEY+token,RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);
        //6，放行
        return true;
    }


    /**
     * 使用session作为登陆的验证
     * */
    public boolean preHandleSession(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1，获取session
        HttpSession session = request.getSession();
        //2，获取session中的用户
        User user = (User) session.getAttribute("user");
        //3，判断用户是否存在
        if (user == null) {
            //4，不存在拦截
            response.setStatus(401);
            return false;
        }
        //5，存在，保存信息到ThreadLocal
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        UserHolder.saveUser(userDTO);

        //6，放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
