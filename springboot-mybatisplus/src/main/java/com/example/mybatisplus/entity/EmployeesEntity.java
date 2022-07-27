package com.example.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author lishizhan
 * @email 1575234570@qq.com
 * @date 2022-07-18 22:51:51
 */
@Data
@TableName("employees")
public class EmployeesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer employeeId;
	/**
	 * 
	 */
	private String firstName;
	/**
	 * 
	 */
	private String lastName;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private String phoneNumber;
	/**
	 * 
	 */
	private String jobId;
	/**
	 * 
	 */
	private Double salary;
	/**
	 * 
	 */
	private Double commissionPct;
	/**
	 * 
	 */
	private Integer managerId;
	/**
	 * 
	 */
	private Integer departmentId;
	/**
	 * 
	 */
	private Date hiredate;

}
