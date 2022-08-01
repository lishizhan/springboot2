package com.example.security07.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.security07.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author : Alishiz
 * @Date : 2022/7/30/0030
 * @email : 1575234570@qq.com
 * @Description :
 */
@RestControllerAdvice(basePackages = {"com.example.security07.controller"})
@Slf4j
public class GlobalException  {

    /**
     * 其他异常
     * */
    @ExceptionHandler(OtherException.class)
    public R otherException(OtherException oe) {
        log.error("运行异常--》{}",oe.getMessage());
        return R.error(oe.getMessage());
    }

    /**
     * token校验异常
     * */
    @ExceptionHandler(TokenAuthenticationFailureException.class)
    public R tokenAuthenticationFailureException(TokenAuthenticationFailureException ke) {
        log.error("token认证失败：{}", ke.getMessage());
        return R.error(ke.getMessage());
    }


    /**
     * 运行时异常处理
     */
    @ExceptionHandler(RuntimeException.class)
    public R runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException：{}", e.getMessage());
        return R.error("系统异常");
    }


}
