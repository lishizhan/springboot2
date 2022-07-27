package org.example.mymvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {


    @RequestMapping("/quick")
    public String quickMethod() {
        System.out.println("quickMethod running.....");
        return "index";
    }


}
