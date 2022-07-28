package com.example.security04traditionweb.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @author lishizhan
 * @version 1.0
 * @description: 自定义验证吗不匹配异常，
 * @date 2022/7/28 17:17
 */
/**
 * 使用自定义的异常类去继承security的异常可以被当成认证不通过处理
 * */
public class CaptchaNotMatchException extends AuthenticationException {

    public CaptchaNotMatchException(String msg, Throwable t) {
        super(msg, t);
    }

    public CaptchaNotMatchException(String msg) {
        super(msg);
    }
}
