package com.example.security07.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : Alishiz
 * @Date : 2021/9/30/0030 20:07
 * @email : 1575234570@qq.com
 * @Description : JwtToken工具类
 */
@Component
public class JwtTokenUtils {
    //荷载用户名key
    public static final String CLAIM_KEY_USERNAME = "sub";
    //Jwt创建时间
    public static final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.secret}")
    private String secret;
    //jwt失效时间
    @Value("${jwt.expiration}")
    private Long expiration;

    //根据用户信息生成token
    public String generateToken(UserDetails userDetails) {
        //准备荷载
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());//创建时间
        return generateToken(claims);

    }

    //从token信息获取用户名
    public String getUserNameFromToken(String token){
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception exception) {
            username=null;
        }
        return username;
    }

    //判断token是否有效
    public boolean validateToken(String token,UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    //判断token是否可以被刷新
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    //刷新token
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }


    //判断token是否失效
    private boolean isTokenExpired(String token) {
        Date date = getExpiredDateFromToken(token);
        return date.before(new Date());
    }

    //从token中获取失效时间
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }


    //从token中获取荷载
    private Claims getClaimsFromToken(String token) {
        Claims c = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return c;
    }


    //根据荷载生成JWT Token
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }

    //生成token失效时间
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }


}
