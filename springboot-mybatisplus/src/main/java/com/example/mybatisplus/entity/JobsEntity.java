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
@TableName("jobs")
public class JobsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String jobId;
	/**
	 * 
	 */
	private String jobTitle;
	/**
	 * 
	 */
	private Integer minSalary;
	/**
	 * 
	 */
	private Integer maxSalary;

}
