package com.example.security07.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.security07.entity.User;
import com.example.security07.service.UserService;
import com.example.security07.vo.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Alishiz
 * @Date : 2022/7/29/0029
 * @email : 1575234570@qq.com
 * @Description :
 */
@RestController
public class HelloController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    @GetMapping("/user/{username}")
    public R hello(@PathVariable(value = "username",required = true) String username){
        String userRedis = stringRedisTemplate.opsForValue().get("security07:user:" + username);
        if (ObjectUtils.isEmpty(userRedis)){
            User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
            if (ObjectUtils.isEmpty(user)) return R.error("用户不存在");
            String userToJson = JSON.toJSONString(user);
            stringRedisTemplate.opsForValue().set("security07:user:"+user.getUsername(),userToJson);
            return R.ok("SUCCESS").put("data",user);
        }
        User user = JSON.parseObject(userRedis, new TypeReference<User>() {
        });
        return R.ok("SUCCESS").put("data",user);
    }

    @GetMapping("/my")
    @PreAuthorize("hasAuthority('root')")
    public R my(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return R.ok().put("authentication",authentication);
    }



    /**
     * 访问权限设置
     * */
    @GetMapping("/delete/{uId}")
    public R delete(@PathVariable("uId") Long uId){
        return R.ok();
    }



}
