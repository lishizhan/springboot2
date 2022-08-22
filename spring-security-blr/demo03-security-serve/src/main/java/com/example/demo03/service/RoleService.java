package com.example.demo03.service;

import com.example.demo03.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户Id查询用户权限信息
     * @param id
     * @return
     */
    List<Role> getUserRolesByUserId(Long id);
}
