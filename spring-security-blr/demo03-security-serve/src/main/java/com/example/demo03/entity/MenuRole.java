package com.example.demo03.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName menu_role
 */
@TableName(value ="menu_role")
@Data
public class MenuRole implements Serializable {
    /**
     * 
     */
    @TableId
    private Long id;

    /**
     * 
     */
    private Long mid;

    /**
     * 
     */
    private Long rid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}