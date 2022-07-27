package com.example.security02.service;

import com.example.security02.dto.R;

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
