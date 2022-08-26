package com.example.mybatisplus.controller;

import com.example.mybatisplus.dao.EmployeesDao;
import com.example.mybatisplus.entity.EmployeesEntity;
import com.example.mybatisplus.service.EmployeesService;
import com.example.mybatisplus.vo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author lishizhan
 * @description: 多种参数传递方式
 * @date 2022/8/25 17:48
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("select/multipleParams")
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
     */
    @GetMapping("params/{id}")
    public R params(@PathVariable("id") Integer id) {
        EmployeesEntity employees = employeesDao.selectParamsOne(id);
        return R.ok(employees);
    }

    /**
     * 传递一个Map参数
     */
    @GetMapping("params/map")
    public R paramsMap(@RequestParam Map<String, Object> map) {
        if (ObjectUtils.isEmpty(map)) {
            map = new HashMap<>();
            map.put("employeeId", 100);
            map.put("lastName", "K_ing");
        }
        EmployeesEntity employees = employeesDao.selectParamsMapOne(map);
        return R.ok(employees);
    }
    /**
     * 传递一个java对象
     */
    /**
     * 传递一个collection
     */
    @GetMapping("params/collect")
    public R paramsCollect(){
        Collection<Integer> ids = new ArrayList<>();
        ids.add(100);
        ids.add(110);
        ids.add(120);
        List<EmployeesEntity> entities = employeesDao.selectParamsCollect(ids);
        return R.ok(entities);
    }
}
