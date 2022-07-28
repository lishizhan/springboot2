package com.lishizhan.aliblog.controller;

import com.lishizhan.aliblog.common.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Alishiz
 * @Date : 2022/7/28/0028
 * @email : 1575234570@qq.com
 * @Description :
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public R hello(){
        return R.ok();
    }
}
