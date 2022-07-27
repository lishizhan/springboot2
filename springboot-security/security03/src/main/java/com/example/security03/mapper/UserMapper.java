package com.example.security03.mapper;

import com.example.security03.entity.Role;
import com.example.security03.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Admin
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-07-27 13:48:17
* @Entity com.example.security03.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户信息
     * */
    User loadUserByUsername(@Param("username") String username);

    /**
     * 根据用户id查询用户的权限信息
     * */
    List<Role> getRoleById(@Param("uid") Long uid);

}




