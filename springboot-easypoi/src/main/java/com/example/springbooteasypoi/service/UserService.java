package com.example.springbooteasypoi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbooteasypoi.entity.User;

import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2022-08-21 21:18:30
 */
public interface UserService extends IService<User> {

    List<User> selectUserWithHobby();

    List<User> getUserAndCart() throws ExecutionException, InterruptedException;

}

