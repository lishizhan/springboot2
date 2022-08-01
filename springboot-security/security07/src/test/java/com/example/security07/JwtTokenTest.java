package com.example.security07;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : Alishiz
 * @Date : 2022/7/30/0030
 * @email : 1575234570@qq.com
 * @Description :
 */
public class JwtTokenTest {


    @Test
    public void createJwtTest() {
        //头信息
        Map<String, Object> header = new HashMap<>();
        //负载
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "zhans");
        payload.put("id", "10000");
        String jwt = JWT.create()
                .withHeader(header) //头信息
                .withClaim("id", 1000) //负载
                .withClaim("name", "zhans") //负载
                .withExpiresAt(new Date(new Date().getTime() + 60000 * 60)) //指定令牌的过期时间
                .sign(Algorithm.HMAC256("sdgkalb"));//密钥
        System.out.println("jwt = " + jwt);
    }

    /*
     * 令牌验证
     *
     * */
    @Test
    public void tokenJwtTest() {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("sdgkalb")).build();
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiemhhbnMiLCJpZCI6MTAwMCwiZXhwIjoxNjU5MTY3NjAwfQ.NPYsugnCcZ548Ss4kUlh4KzwPaYG1i3lq0ohh8jqANQ");
        /*获取负载信息*/
        Integer id = verify.getClaims().get("id").asInt();
        String name = verify.getClaims().get("name").asString();
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        Date expiresAt = verify.getExpiresAt();
        //过期时间
        System.out.println("expiresAt = " + expiresAt);
    }


}
