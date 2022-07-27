package com.example.security01.service;

import com.example.security01.dto.R;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  {

    /*
     * 登陆接口
     * */
    R login(String phone_or_email, String password);

    /*
    * 获取所有用户
    * */
    R getUserList();

}
