package org.example.mvc3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","Hello World" );
        mv.setViewName("result");
        return mv;
    }
    @RequestMapping(value = "/hello.do")
    @ResponseBody
    public String hello(){
        return "Hello Spring MVC";
    }
    @RequestMapping(value = "/other")
    @ResponseBody
    public String other(){
        return "Hello Spring MVC other";
    }

    @RequestMapping(value = "/myView")
    public String myView(Model model){
        model.addAttribute("data","Hello");
        System.out.println("------------------------->>>");
        return "myView";
    }
    @RequestMapping(value = "/target123")
    public String target(Model model){
        model.addAttribute("data","Hello");
        System.out.println("------------------------->>>");
        return "target";
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
