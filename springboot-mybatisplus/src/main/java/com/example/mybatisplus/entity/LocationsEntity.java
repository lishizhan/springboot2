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
@TableName("locations")
public class LocationsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer locationId;
	/**
	 * 
	 */
	private String streetAddress;
	/**
	 * 
	 */
	private String postalCode;
	/**
	 * 
	 */
	private String city;
	/**
	 * 
	 */
	private String stateProvince;
	/**
	 * 
	 */
	private String countryId;

}
