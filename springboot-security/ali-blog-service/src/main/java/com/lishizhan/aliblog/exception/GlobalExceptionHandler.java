package com.lishizhan.aliblog.exception;

import com.lishizhan.aliblog.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/7/29 13:55
 */
@RestControllerAdvice
@Component
@Slf4j
public class GlobalExceptionHandler {

    //捕获MethodArgumentNotValidException类型的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();

        Map<String, String> map = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return R.error(HttpStatus.BAD_REQUEST.value(),"提交的数据不合法").put("data",map);
    }

    @ExceptionHandler(ArgsErrException.class)
    public R ArgsErrExceptionHandler(ArgsErrException e){
        log.error("参数校验异常：{}",e.getMessage());
        return R.error(e.getMessage());
    }

    /**
     * 运行时异常处理
     * */
    @ExceptionHandler(RuntimeException.class)
    public R runtimeExceptionHandler(RuntimeException e){
        log.error("RuntimeException：{}",e.getMessage());
        return R.error(e.getMessage());
    }
}
