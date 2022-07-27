package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.common.utils.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.common.utils.Query;

import com.example.mybatisplus.dao.LocationsDao;
import com.example.mybatisplus.entity.LocationsEntity;
import com.example.mybatisplus.service.LocationsService;


@Service("locationsService")
public class LocationsServiceImpl extends ServiceImpl<LocationsDao, LocationsEntity> implements LocationsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LocationsEntity> page = this.page(
                new Query<LocationsEntity>().getPage(params),
                new QueryWrapper<LocationsEntity>()
        );

        return new PageUtils(page);
    }

}