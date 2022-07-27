package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.common.utils.PageUtils;
import com.example.mybatisplus.entity.JobsEntity;

import java.util.Map;

/**
 * 
 *
 * @author lishizhan
 * @email 1575234570@qq.com
 * @date 2022-07-18 22:51:51
 */
public interface JobsService extends IService<JobsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

