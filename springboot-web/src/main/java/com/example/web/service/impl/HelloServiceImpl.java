package com.example.web.service.impl;

import com.example.web.service.HelloService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public List<String> users() {

        List<String> list = new ArrayList<>();
        list.add("zhans");
        list.add("lisi");

        return list;
    }
}
