package com.example.security07.service;

import com.example.security07.dto.LoginDto;
import com.example.security07.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.security07.vo.R;

/**
 *
 */
public interface UserService extends IService<User> {

    R login(LoginDto loginDto);

    R logout();

}
