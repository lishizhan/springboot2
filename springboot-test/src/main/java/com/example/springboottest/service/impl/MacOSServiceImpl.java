package com.example.springboottest.service.impl;

import com.example.springboottest.service.OSService;
import org.springframework.stereotype.Service;

/**
 * @Author : Alishiz
 * @Date : 2022/6/17/0017
 * @email : 1575234570@qq.com
 * @Description : 苹果操作系统
 */
@Service
public class MacOSServiceImpl implements OSService {
    @Override
    public void showOsInfo() {
        System.out.println("这是MAC系统········");
    }
}
