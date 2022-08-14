package com.example.demo02.mapper;

import com.example.demo02.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.example.demo02.entity.Role
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询用户角色
     * @param id
     * @return
     */
    List<Role> selectUserRoleByUserId(@Param("id") Long id);
}




