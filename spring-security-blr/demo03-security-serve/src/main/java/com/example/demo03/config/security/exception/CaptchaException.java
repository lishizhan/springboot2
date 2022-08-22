package com.example.demo03.config.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author : lishizhan
 * @Date : 2022/8/13/0013
 * @email : 1575234570@qq.com
 * @Description : 自定义验证码异常
 */
public class CaptchaException extends AuthenticationException {
    public CaptchaException(String msg, Throwable t) {
        super(msg, t);
    }

    public CaptchaException(String msg) {
        super(msg);
    }
}
