package com.example.springbootredis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootredis.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> selectUserAll();

}
