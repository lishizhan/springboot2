package com.example.mybatisplus.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.example.mybatisplus.common.utils.PageUtils;
import com.example.mybatisplus.dao.EmployeesDao;
import com.example.mybatisplus.vo.DeptAvgVo;
import com.example.mybatisplus.vo.EmpAndDeptVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.mybatisplus.entity.EmployeesEntity;
import com.example.mybatisplus.service.EmployeesService;
import com.example.mybatisplus.common.utils.R;



/**
 *
 * @author lishizhan
 * @email 1575234570@qq.com
 * @date 2022-07-18 22:51:51
 */
@RestController
@RequestMapping("mybatisplus/employees")
public class EmployeesController {
    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private EmployeesDao employeesDao;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = employeesService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{employeeId}")
    public R info(@PathVariable("employeeId") Integer employeeId){
		EmployeesEntity employees = employeesService.getById(employeeId);

        return R.ok().put("employees", employees);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody EmployeesEntity employees){
		employeesService.save(employees);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody EmployeesEntity employees){
		employeesService.updateById(employees);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] employeeIds){
		employeesService.removeByIds(Arrays.asList(employeeIds));

        return R.ok();
    }

    /*
    * 案例：查询员工名、部门名
    * */

    @GetMapping("/getEmpNameAndDept")
    public R getEmpNameAndDept(){
        List<EmpAndDeptVo> empAndDeptVos =  employeesDao.getEmpNameAndDept();
        return R.ok().put("empAndDeptVo",empAndDeptVos);
    }

    /*
    * 查询每个部门的平均工资
    * */
    @GetMapping("/deptAvgSalary")
    public List<DeptAvgVo> deptAvgSalary(){
        return employeesDao.deptAndSalaryAvg();
    }
}
