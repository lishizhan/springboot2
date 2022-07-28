package com.lishizhan.aliblog.mapper;

import com.lishizhan.aliblog.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.lishizhan.aliblog.entity.Role
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRoleById(@Param("uid") Long uid);
}




