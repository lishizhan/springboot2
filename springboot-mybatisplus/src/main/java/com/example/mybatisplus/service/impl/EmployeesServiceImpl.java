package com.example.mybatisplus.service.impl;


import com.example.mybatisplus.common.utils.PageUtils;
import com.example.mybatisplus.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.mybatisplus.dao.EmployeesDao;
import com.example.mybatisplus.entity.EmployeesEntity;
import com.example.mybatisplus.service.EmployeesService;


@Service("employeesService")
public class EmployeesServiceImpl extends ServiceImpl<EmployeesDao, EmployeesEntity> implements EmployeesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EmployeesEntity> page = this.page(
                new Query<EmployeesEntity>().getPage(params),
                new QueryWrapper<EmployeesEntity>()
        );

        return new PageUtils(page);
    }

}