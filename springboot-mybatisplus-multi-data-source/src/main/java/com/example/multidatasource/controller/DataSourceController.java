package com.example.multidatasource.controller;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.example.multidatasource.vo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Set;

/**
 * @Author : lishizhan
 * @Date : 2022/8/22/0022
 * @email : 1575234570@qq.com
 * @Description :
 */
@RestController
@RequestMapping("dataSource")
@RequiredArgsConstructor
public class DataSourceController {

    private final DataSource dataSource;

    @GetMapping("now")
    public R now(){
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        Map<String, DataSource> dataSources = ds.getDataSources();
        //获取数据源标识
        Set<String> keySet = dataSources.keySet();





        return R.ok(keySet);
    }

}
