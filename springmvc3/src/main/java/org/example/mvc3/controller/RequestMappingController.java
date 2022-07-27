package org.example.mvc3.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

/**
@RequestMapping标识一个类：设置映射请求的请求路径的初始信息
@RequestMapping标识一个方法：设置映射请求请求路径的具体信息
 */
@Controller
@RequestMapping("requestMapping")
public class RequestMappingController {

    @RequestMapping(value = {"r1","r2","r3"},method = RequestMethod.GET)
    public String r1(HttpServletRequest request, Model model) {
        StringBuffer url = request.getRequestURL();
        System.out.println("uri = " + url);
        System.out.println(" r1 ");
        model.addAttribute("data","success");
        return "success";
    }

    @RequestMapping(value = "r4",method = RequestMethod.POST)
    public String r4(){
        System.out.println(" ---mvc3--- ");
        return "success";
    }

    /**
     * params请求参数必须携带username
     * headers 携带的请求头信息
     * @return
     */
    @RequestMapping(value = "r5",params = {"username=zhans"},headers = {"Host=localhost:8080"})
    public ModelAndView r5(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        modelAndView.addObject("data","success");
        return modelAndView;
    }

}
