package org.example.mvc3.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/*
原始方式：/deleteUser?id=1
rest方式：/deleteUser/1
    SpringMVC路径中的占位符常用于RESTful风格中，当请求路径中将某些数据通过路径的方式传输到服
务器中，就可以在相应的@RequestMapping注解的value属性中通过占位符{xxx}表示传输的数据，在
通过@PathVariable注解，将占位符所表示的数据赋值给控制器方法的形参
* */
//SpringMVC支持路径中的占位符（重点）
@RestController
public class PathVariableController {

    @RequestMapping("/test/{id}")
    public String test1(@PathVariable("id") Long id) {
        System.out.println("id = " + id);
        return "SUCCESS：" + id;
    }

}
