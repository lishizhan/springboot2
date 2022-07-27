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

import com.example.mybatisplus.entity.LocationsEntity;
import com.example.mybatisplus.service.LocationsService;
import com.example.mybatisplus.common.utils.R;



/**
 * 
 *
 * @author lishizhan
 * @email 1575234570@qq.com
 * @date 2022-07-18 22:51:51
 */
@RestController
@RequestMapping("mybatisplus/locations")
public class LocationsController {
    @Autowired
    private LocationsService locationsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = locationsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{locationId}")
    public R info(@PathVariable("locationId") Integer locationId){
		LocationsEntity locations = locationsService.getById(locationId);

        return R.ok().put("locations", locations);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LocationsEntity locations){
		locationsService.save(locations);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody LocationsEntity locations){
		locationsService.updateById(locations);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] locationIds){
		locationsService.removeByIds(Arrays.asList(locationIds));

        return R.ok();
    }

}
