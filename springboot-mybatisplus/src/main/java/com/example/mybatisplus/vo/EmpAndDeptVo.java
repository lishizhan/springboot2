package com.example.mybatisplus.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : Alishiz
 * @Date : 2022/7/18/0018
 * @email : 1575234570@qq.com
 * @Description :
 */
@Data
public class EmpAndDeptVo implements Serializable{

    private Integer employeeId;
    private String lastName;

    private Integer departmentId;
    private String departmentName;
}
