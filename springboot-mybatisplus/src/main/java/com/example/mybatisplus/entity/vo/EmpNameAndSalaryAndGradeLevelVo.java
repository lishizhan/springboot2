package com.example.mybatisplus.entity.vo;

import lombok.Data;

/**
 * @author lishizhan
 * @description: 员工姓名、工资、等级
 * @date 2022/8/25 16:21
 */
@Data
public class EmpNameAndSalaryAndGradeLevelVo {
    private String lastName;
    private Double salary;
    private String gradeLevel;
}
