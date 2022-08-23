package com.example.dume.dataserver1.controller;

import com.example.dume.dataserver1.service.impl.MainTableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dume
 * @create 2021-12-24 15:59
 **/
@RestController
@RequestMapping("/data")
public class MainTableController {
    @Autowired
    private MainTableServiceImpl mainTableServiceImpl;

    /**
     * 查看主数据源数据
     * @return
     */
    @PostMapping("/interface1")
    public List getMainTableList(){
        return  mainTableServiceImpl.selectList();
    }
}
