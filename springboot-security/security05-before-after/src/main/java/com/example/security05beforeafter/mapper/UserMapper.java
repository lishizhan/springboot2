package com.example.security05beforeafter.mapper;

import com.example.security05beforeafter.entity.Role;
import com.example.security05beforeafter.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.example.security05beforeafter.entity.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户id查询用户的权限信息
     * */
    List<Role> getRoleById(@Param("uid") Long uid);
}




