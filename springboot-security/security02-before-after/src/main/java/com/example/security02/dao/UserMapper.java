package com.example.security02.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.security02.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
