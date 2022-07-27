package com.example.springbootjwt.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.example.springbootjwt.pojo.User;
import com.example.springbootjwt.service.UserService;
import com.example.springbootjwt.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接口受保护，需要验证接口的正确性
 * */
@RestController
@Slf4j
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public Map<String,Object> userInfo(@RequestHeader(value = "token",required = false) String token){
        log.info("[ApiController] [userInfo] 用户携带的token={}",token);
        Map<String, Object> map = new HashMap<>();
        try {
            JWTUtil.verify(token);
            Map<String, Claim> payload = JWTUtil.getPayloadFromToken(token);
            String id = payload.get("id").asString();
            User user = userService.getUser(Long.parseLong(id));
            log.info("认证成功~");
            map.put("msg","SUCCESS");
            map.put("user",user);
            map.put("code",0);
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg", "签名不⼀致");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg", "令牌过期");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg", "算法不匹配");
        } catch (InvalidClaimException e) {
            e.printStackTrace();
            map.put("msg", "失效的payload");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "token⽆效");
        }
        return map;
    }

    @GetMapping("/users")
    public List<User> users(){
        return userService.selectUserAll();
    }





}
