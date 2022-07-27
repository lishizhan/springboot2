package com.example.security03.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName user
 */

@Data
public class UserVo implements Serializable {

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 是否启用
     */
    private Integer enabled;

    /**
     * 
     */
    private Integer accountNonExpired;

    /**
     * 
     */
    private Integer accountNonLocked;

    /**
     * 
     */
    private Integer credentialsNonExpired;

}