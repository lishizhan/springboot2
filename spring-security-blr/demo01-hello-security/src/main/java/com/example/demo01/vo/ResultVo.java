package com.example.demo01.vo;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : Alishiz
 * @Date : 2022/7/25/0025
 * @email : 1575234570@qq.com
 * @Description :
 */
public class ResultVo extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public ResultVo() {
        put("code", HttpStatus.OK.value());
    }

    public static ResultVo error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
    }

    public static ResultVo error(String msg) {
        return error(500, msg);
    }

    public static ResultVo error(int code, String msg) {
        ResultVo r = new ResultVo();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static ResultVo ok(String msg) {
        ResultVo r = new ResultVo();
        r.put("msg", msg);
        return r;
    }

    public static ResultVo ok(Map<String, Object> map) {
        ResultVo r = new ResultVo();
        r.putAll(map);
        return r;
    }

    public static ResultVo ok() {
        return new ResultVo();
    }

    public ResultVo put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
