package com.example.mybatisplus.vo;

import lombok.Data;

@Data
public class DeptAvgVo{
    private Integer departmentId;
    private String departmentName;
    /*平均工资*/
    private Double salaryAvg;
}
