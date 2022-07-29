package com.lishizhan.aliblog.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @author lishizhan
 * @version 1.0
 * @description: 验证吗错误异常
 * @date 2022/7/29 10:30
 */
public class CodeErrException extends AuthenticationException {

    public CodeErrException(String msg, Throwable t) {
        super(msg, t);
    }

    public CodeErrException(String msg) {
        super(msg);
    }
}
