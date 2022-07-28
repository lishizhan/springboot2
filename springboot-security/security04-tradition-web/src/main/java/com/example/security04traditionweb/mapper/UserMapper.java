package com.example.security04traditionweb.mapper;

import com.example.security04traditionweb.entity.Role;
import com.example.security04traditionweb.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.example.security04traditionweb.entity.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户id查询用户的权限信息
     * */
    List<Role> getRoleById(@Param("uid") Long uid);
}




