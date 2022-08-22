package com.example.demo02.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo02.entity.Role;
import com.example.demo02.entity.UserRole;
import com.example.demo02.service.RoleService;
import com.example.demo02.mapper.RoleMapper;
import com.example.demo02.service.UserRoleService;
import com.example.demo02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 *
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> getUserRolesByUserId(Long id) {
        return roleMapper.selectUserRoleByUserId(id);
    }
}




