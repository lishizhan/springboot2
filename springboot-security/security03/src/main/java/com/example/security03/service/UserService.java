package com.example.security03.service;

import com.example.security03.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Admin
* @description 针对表【user】的数据库操作Service
* @createDate 2022-07-27 13:48:17
*/
public interface UserService extends IService<User> {

    List<User> getUserList();

}
