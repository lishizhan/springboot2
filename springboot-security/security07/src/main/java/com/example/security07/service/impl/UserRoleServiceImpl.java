package com.example.security07.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.security07.entity.UserRole;
import com.example.security07.service.UserRoleService;
import com.example.security07.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author Admin
* @description 针对表【user_role】的数据库操作Service实现
* @createDate 2022-08-01 10:24:24
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




