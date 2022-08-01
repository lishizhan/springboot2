package com.example.security06debug.controller;

import com.example.security06debug.vo.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/7/29 17:42
 */
@RestController
public class UserController {




    @GetMapping("/users")
    public R hello(){
        Map<String, Object> map = new HashMap<>();
        map.put("name","zhans");
        map.put("age",18);
        map.put("phone","18666645874");
        return R.ok(map);
    }

}
