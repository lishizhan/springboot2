package com.example.security07.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : Alishiz
 * @Date : 2022/7/13/0013
 * @email : 1575234570@qq.com
 * @Description : JWT工具类
 */
public class JWTUtil {
    //密钥
    public static String SECRET = "lishizhan666";
    public static Long EXPIRATION = 604800L;

    public static String getToken(Map<String, String> payload) {
        JWTCreator.Builder builder = JWT.create();
        payload.forEach(builder::withClaim);
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 3); //默认3天过期
        builder.withExpiresAt(instance.getTime()); //指定令牌的过期时间
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 验证token
     */
    public static DecodedJWT verify(String token) {
        //如果有任何验证异常，此处都会抛出异常
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    /**
     * 获取token中的payload
     */
    public static Map<String, Claim> getPayloadFromToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token).getClaims();
    }



}
