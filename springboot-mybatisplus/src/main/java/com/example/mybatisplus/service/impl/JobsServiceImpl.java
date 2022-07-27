package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.common.utils.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.common.utils.Query;

import com.example.mybatisplus.dao.JobsDao;
import com.example.mybatisplus.entity.JobsEntity;
import com.example.mybatisplus.service.JobsService;


@Service("jobsService")
public class JobsServiceImpl extends ServiceImpl<JobsDao, JobsEntity> implements JobsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<JobsEntity> page = this.page(
                new Query<JobsEntity>().getPage(params),
                new QueryWrapper<JobsEntity>()
        );

        return new PageUtils(page);
    }

}