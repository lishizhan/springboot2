package com.example.mybatisplus.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.entity.EmployeesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.entity.vo.EmpNameAndDeptNameVo;
import com.example.mybatisplus.entity.vo.EmpNameAndJobIdAndJobTitleVo;
import com.example.mybatisplus.entity.vo.EmpNameAndSalaryAndGradeLevelVo;
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

    Page<EmpNameAndSalaryAndGradeLevelVo> notEqPage(Page<EmpNameAndSalaryAndGradeLevelVo> page);

    /**
     * 查询员工名所对应的部门名
     * @param page
     * @return
     */
    Page<EmpNameAndDeptNameVo> epPage(Page<EmpNameAndDeptNameVo> page);

    /**
     * 查询员工名，工种号，工种名
     * @param page
     * @return
     */
    Page<EmpNameAndJobIdAndJobTitleVo> epPage2(Page<EmpNameAndJobIdAndJobTitleVo> page);

    /**
     * 查询有奖金的员工名和部门名
     * @param page
     * @return
     */
    Page<EmpNameAndDeptNameVo> epPage3(Page<EmpNameAndDeptNameVo> page);

    /**
     * 传递一个参数
     * @param lastName
     * @return
     */
    EmployeesEntity selectParamsOne(String lastName);
}
