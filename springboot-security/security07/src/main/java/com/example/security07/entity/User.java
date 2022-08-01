package com.example.security07.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId
    private Long id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Boolean accountNonExpired;

    /**
     * 
     */
    private Boolean accountNonLocked;

    /**
     * 
     */
    private Boolean credentialsNonExpired;

    /**
     * 
     */
    private Boolean enabled;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}