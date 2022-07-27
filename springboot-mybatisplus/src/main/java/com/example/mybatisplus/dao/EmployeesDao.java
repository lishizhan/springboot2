package com.example.mybatisplus.dao;

import com.example.mybatisplus.entity.EmployeesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.vo.DeptAvgVo;
import com.example.mybatisplus.vo.EmpAndDeptVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author lishizhan
 * @email 1575234570@qq.com
 * @date 2022-07-18 22:51:51
 */
@Mapper
public interface EmployeesDao extends BaseMapper<EmployeesEntity> {

    /*
    * 案例：查询员工名、部门名
    * */
    List<EmpAndDeptVo> getEmpNameAndDept();

    /*
    * 查询工资总和
    * */
    Double getSalarySum();

    Double getSalaryMax();

    List<DeptAvgVo> deptAndSalaryAvg();
}
