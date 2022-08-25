package com.example.mybatisplus.controller;

import com.example.mybatisplus.dao.EmployeesDao;
import com.example.mybatisplus.entity.EmployeesEntity;
import com.example.mybatisplus.service.EmployeesService;
import com.example.mybatisplus.vo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lishizhan
 * @description: 多种参数传递方式
 * @date 2022/8/25 17:48
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("multipleParams")
public class MultipleParamsController {

    private final EmployeesDao employeesDao;

    /**
     * 传递一个参数
     * 传递一个Map参数
     * 传递一个javabean参数
     * 多参数中用@param指定参数名称
     * java编译中参数名称的处理
     * mapper接口传参源码分析
     * 传递1个Collection参数
     * 传递1个List参数
     * 传递1个数组参数
     */

    /**
     * 传递一个参数
     * */
    @GetMapping("params/{lastName}")
    public R params(@PathVariable("lastName") String lastName){
        EmployeesEntity employees = employeesDao.selectParamsOne(lastName);
        return R.ok(employees);
    }

}
