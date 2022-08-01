package com.example.security07.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;

/**
 * @Author : Alishiz
 * @Date : 2022/7/30/0030
 * @email : 1575234570@qq.com
 * @Description :
 */
public class TokenAuthenticationFailureException extends RuntimeException {

    public TokenAuthenticationFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenAuthenticationFailureException(String message) {
        super(message);
    }
}
