package com.hmdp.intercepter;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import com.hmdp.utils.RedisConstants;
import com.hmdp.utils.UserHolder;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author : Alishiz
 * @Date : 2022/7/17/0017
 * @email : 1575234570@qq.com
 * @Description : 实现拦截器，实现登陆拦截
 */
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //由于第一层拦截器已经将有用户信息的状态放进ThreadLocal中
        //判断是否由用户，有就放行
        if (UserHolder.getUser() == null) {
            //没有该用户则拦截请求
            response.setStatus(401);
            return false;
        }
        //有用户则放行
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
