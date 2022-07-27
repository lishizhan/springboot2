package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.common.utils.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.common.utils.Query;

import com.example.mybatisplus.dao.DepartmentsDao;
import com.example.mybatisplus.entity.DepartmentsEntity;
import com.example.mybatisplus.service.DepartmentsService;


@Service("departmentsService")
public class DepartmentsServiceImpl extends ServiceImpl<DepartmentsDao, DepartmentsEntity> implements DepartmentsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DepartmentsEntity> page = this.page(
                new Query<DepartmentsEntity>().getPage(params),
                new QueryWrapper<DepartmentsEntity>()
        );

        return new PageUtils(page);
    }

}