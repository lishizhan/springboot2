package org.example.mvc3.controller;

import org.example.mvc3.pojo.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * SpringMVC获取请求参数
 */
/*
 *
 *
 * */
@RestController
public class RequestParamController {
    /**
     * 通过ServletAPI获取
     * 将HttpServletRequest作为控制器方法的形参，此时HttpServletRequest类型的参数表示封装了当前请求的请求报文的对象
     */
    @GetMapping("/paramServletTest")
    public String paramServletTest(HttpServletRequest request) {
        String username = request.getParameter("username");
        return "SUCCESS：" + username;

    }

    /**
     * 通过控制器方法的形参获取请求参数
     * 在控制器方法的形参位置，设置和请求参数同名的形参，当浏览器发送请求，匹配到请求映射时，在DispatcherServlet中就会将请求参数赋值给相应的形参
     */
    @GetMapping("/paramTest")
    public String paramTest(String username){
        return "SUCCESS：" + username;
    }
    @GetMapping("/paramTest2")
    public String[] paramTest2(String[] username){
        return username;
    }

    /**
     * @RequestParam：若请求参数与接口参数名不一致则使用该注解进行映射
     @RequestParam注解一共有三个属性：

     加入@RequestMapping后该属性必须要传输，因为required默认为true

     value：指定为形参赋值的请求参数的参数名
     required：设置是否必须传输此请求参数，默认值为true
     若设置为true时，则当前请求必须传输value所指定的请求参数，若没有传输该请求参数，且没有设置
     defaultValue属性，则页面报错400：Required String parameter 'xxx' is not present；若设置为
     false，则当前请求不是必须传输value所指定的请求参数，若没有传输，则注解所标识的形参的值为
     null
     defaultValue：不管required属性值为true或false，当value所指定的请求参数没有传输或传输的值
     为""时，则使用默认值为形参赋值
     * */
    @GetMapping("/testRequestParam")
    public String testRequestParam(
            @RequestParam(value = "username",required = true,defaultValue = "zhans") String user_name,
            @RequestParam("password") String pwd){
        return "SUCCESS-->用户名："+user_name+" 密码："+pwd;
    }


    /**
     * 获取请求头信息
     * @RequestHeader
     * @RequestHeader是将请求头信息和控制器方法的形参创建映射关系
     * @RequestHeader注解一共有三个属性：value、required、defaultValue，用法同@RequestParam
     * */
    @GetMapping("/requestHeaderTest")
    public String requestHeaderTest(@RequestHeader("host") String host){
        System.out.println("host = " + host);
        return "SUCCESS："+host;
    }

    /**
     * 获取cookie数据
     * @CookieValue是将cookie数据和控制器方法的形参创建映射关系
     * @CookieValue注解一共有三个属性：value、required、defaultValue，用法同@RequestParam
     *
     * */

    @GetMapping("/cookieTest")
    public String cookieTest(@CookieValue("JSESSIONID") String jSessionId){
        return "SUCCESS："+jSessionId;
    }

    /**
     * 通过实体类对象属性获取请求参数
     * @RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；而最常用的使用请求体传参的无疑是POST请求了，所以使用@RequestBody接收数据时，一般都用POST方式进行提交。在后端的同一个接收方法里，@RequestBody与@RequestParam()可以同时使用，@RequestBody最多只能有一个，而@RequestParam()可以有多个。
     * */
    @PostMapping("/pojoParamsTest")
    public User pojoParamsTest(@RequestBody User user){
        System.out.println("user = " + user);
        return user;
    }
    /**
     * 如果用form表单提交则不需要加@RequestBody
     * */
    @PostMapping("/pojoParamsFormTest")
    public User pojoParamsFormTest(User user){
        System.out.println("user = " + user);
        return user;
    }


}
