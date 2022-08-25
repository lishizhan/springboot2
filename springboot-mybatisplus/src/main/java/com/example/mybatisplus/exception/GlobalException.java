package com.example.mybatisplus.exception;

import com.example.mybatisplus.vo.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lishizhan
 * @description:
 * @date 2022/8/25 14:02
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    public R exception(RuntimeException e){
        e.printStackTrace();
        return R.error(500,e.getMessage());
    }
}
