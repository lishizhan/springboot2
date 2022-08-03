package com.example.security07.mapper;

import com.example.security07.entity.Role;
import com.example.security07.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.example.security07.entity.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<Role> getRoleById(@Param("uid") Long uid);

}




