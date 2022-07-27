package com.example.mybatisplus.controller;

import java.util.Arrays;
import java.util.Map;

import com.example.mybatisplus.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.mybatisplus.entity.DepartmentsEntity;
import com.example.mybatisplus.service.DepartmentsService;
import com.example.mybatisplus.common.utils.R;



/**
 * 
 *
 * @author lishizhan
 * @email 1575234570@qq.com
 * @date 2022-07-18 22:51:51
 */
@RestController
@RequestMapping("mybatisplus/departments")
public class DepartmentsController {
    @Autowired
    private DepartmentsService departmentsService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = departmentsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{departmentId}")
    public R info(@PathVariable("departmentId") Integer departmentId){
		DepartmentsEntity departments = departmentsService.getById(departmentId);

        return R.ok().put("departments", departments);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody DepartmentsEntity departments){
		departmentsService.save(departments);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody DepartmentsEntity departments){
		departmentsService.updateById(departments);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Integer[] departmentIds){
		departmentsService.removeByIds(Arrays.asList(departmentIds));

        return R.ok();
    }

}
