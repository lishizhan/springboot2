package com.example.security03.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.security03.entity.Role;
import com.example.security03.service.RoleService;
import com.example.security03.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author Admin
* @description 针对表【role(角色)】的数据库操作Service实现
* @createDate 2022-07-27 13:47:22
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




