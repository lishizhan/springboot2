package com.example.security07;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.security07.utils.JWTUtil;
import com.example.security07.utils.JwtTokenUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Security07ApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
    }

    @Test
    public void pwdTest() {
        String encode = passwordEncoder.encode("123123");
        System.out.println("encode = " + encode);

    }

    @Test
    public void JWTUtil() {
        //生成JWT
        Map<String, String> map = new HashMap<>();
        map.put("name", "lishizhan");
        map.put("id", "123123");
        String token = JWTUtil.getToken(map);
        System.out.println("token = " + token);
        Map<String, Claim> payload = JWTUtil.getPayloadFromToken(token);
        String id = payload.get("id").asString();
        String name = payload.get("name").asString();
        System.out.println("name = " + name);
        System.out.println("id = " + id);
    }

    @Test
    public void tokenTest() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjE1NTIxODcxMTU0NzAxODAzNTMiLCJleHAiOjE2NTk0MDk1NjMsInVzZXJuYW1lIjoicm9vdCJ9.BcRHFR-Ti3CbhBcnjceEzgDgouXcB2Q9neJj-v6fXYo1";

        DecodedJWT verify = JWTUtil.verify(token);

        Map<String, Claim> payload = JWTUtil.getPayloadFromToken(token);
        payload.forEach((k, v) -> {
            System.out.println(k+ " = " + v.asString());
        });
    }


    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    public void testJwtTokenUtils(){


    }

}
