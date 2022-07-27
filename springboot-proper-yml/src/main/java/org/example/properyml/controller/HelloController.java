package org.example.properyml.controller;

import org.example.properyml.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private Person person;

    @GetMapping("hello")
    public Person hello(){
        return person;
    }
}
