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
@TableName("departments")
public class DepartmentsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer departmentId;
	/**
	 * 
	 */
	private String departmentName;
	/**
	 * 
	 */
	private Integer managerId;
	/**
	 * 
	 */
	private Integer locationId;

}
