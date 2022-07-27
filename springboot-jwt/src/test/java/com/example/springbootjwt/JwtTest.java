package com.example.springbootjwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @Author : Alishiz
 * @Date : 2022/7/13/0013
 * @email : 1575234570@qq.com
 * @Description :
 */
public class JwtTest {


    /*
     * JWT的基本使用
     * 获取令牌
     * */
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
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiemhhbnMiLCJpZCI6MTAwMCwiZXhwIjoxNjU3NzI2OTQ2fQ.PqgF6rk53kkOxoAxJK9FZX-qS707KP7VfntQ-aQTVME");
        /*获取负载信息*/
        Integer id = verify.getClaims().get("id").asInt();
        String name = verify.getClaims().get("name").asString();
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        Date expiresAt = verify.getExpiresAt();
        //过期时间
        System.out.println("expiresAt = " + expiresAt);
    }

    @Test
    public void bbbTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhans");
        map.put("age", 23);




        map.forEach((s, o) -> System.out.println(s + " = " + o));


    }
}
