package com.lishizhan.aliblog.service;

import com.lishizhan.aliblog.common.R;
import com.lishizhan.aliblog.dto.UserRegisterDto;
import com.lishizhan.aliblog.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface UserService extends IService<User> {
    R register(UserRegisterDto userRegisterDto);
}
