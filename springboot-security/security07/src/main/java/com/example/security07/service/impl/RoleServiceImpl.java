package com.example.security07.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.security07.entity.Role;
import com.example.security07.service.RoleService;
import com.example.security07.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author Admin
* @description 针对表【role】的数据库操作Service实现
* @createDate 2022-08-01 10:24:10
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




