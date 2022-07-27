package com.example.mybatisplus.controller;

import java.util.Arrays;
import java.util.Map;

import com.example.mybatisplus.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mybatisplus.entity.JobsEntity;
import com.example.mybatisplus.service.JobsService;
import com.example.mybatisplus.common.utils.R;



/**
 * 
 *
 * @author lishizhan
 * @email 1575234570@qq.com
 * @date 2022-07-18 22:51:51
 */
@RestController
@RequestMapping("mybatisplus/jobs")
public class JobsController {
    @Autowired
    private JobsService jobsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = jobsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{jobId}")
    public R info(@PathVariable("jobId") String jobId){
		JobsEntity jobs = jobsService.getById(jobId);

        return R.ok().put("jobs", jobs);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JobsEntity jobs){
		jobsService.save(jobs);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody JobsEntity jobs){
		jobsService.updateById(jobs);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] jobIds){
		jobsService.removeByIds(Arrays.asList(jobIds));

        return R.ok();
    }

}
