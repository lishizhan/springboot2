package com.example.springbooteasypoi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbooteasypoi.entity.Hobby;
import com.example.springbooteasypoi.mapper.HobbyMapper;
import com.example.springbooteasypoi.service.HobbyService;
import org.springframework.stereotype.Service;

/**
 * (Hobby)表服务实现类
 *
 * @author makejava
 * @since 2022-08-21 22:36:11
 */
@Service("hobbyService")
public class HobbyServiceImpl extends ServiceImpl<HobbyMapper, Hobby> implements HobbyService {

}
