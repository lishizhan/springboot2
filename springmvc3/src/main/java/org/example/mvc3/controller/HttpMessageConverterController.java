package org.example.mvc3.controller;

import org.example.mvc3.pojo.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 报文信息转换器
 * */

/**
HttpMessageConverter，报文信息转换器，将请求报文转换为Java对象，或将Java对象转换为响应报文
HttpMessageConverter提供了两个注解和两个类型：
                            @RequestBody，@ResponseBody，
                            RequestEntity，ResponseEntity
* */
@RestController
public class HttpMessageConverterController {

    /**
     @RequestBody可以获取请求体，需要在控制器方法设置一个形参，使用@RequestBody进行标识，当前请求的请求体就会为当前注解所标识的形参赋值
     * */
    @PostMapping("/requestBodyTest")
    public String requestBodyTest(@RequestBody String body){
        System.out.println("body = " + body);
        return body;
        //id=100&name=zhans&age=18&email=123123%40qq.com
    }

    /**
     * RequestEntity封装请求报文的一种类型，需要在控制器方法的形参中设置该类型的形参，当前请求的
     * 请求报文就会赋值给该形参，可以通过getHeaders()获取请求头信息，通过getBody()获取请求体信息
     * */
    @GetMapping("/requestEntityTest1")
    public RequestEntity requestEntityTest1(RequestEntity<String> requestEntity){
        System.out.println("requestEntity = " + requestEntity);
        HttpHeaders headers = requestEntity.getHeaders();
        System.out.println("headers = " + headers);
        return requestEntity;
    }
    /**
     * Spring MVC 处理Json数据
     * */
    @PostMapping("/jsonTest")
    public User jsonTest(){
        return null;
    }



}
