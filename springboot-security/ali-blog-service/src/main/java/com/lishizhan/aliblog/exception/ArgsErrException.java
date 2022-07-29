package com.lishizhan.aliblog.exception;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/7/29 14:01
 */
public class ArgsErrException extends RuntimeException{
    public ArgsErrException() {
        super();
    }

    public ArgsErrException(String message) {
        super(message);
    }

}
