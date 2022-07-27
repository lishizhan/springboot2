package com.example.security03.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.security03.entity.UserRole;
import com.example.security03.service.UserRoleService;
import com.example.security03.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author Admin
* @description 针对表【user_role】的数据库操作Service实现
* @createDate 2022-07-27 13:48:23
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




