package com.example.demo02.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户ID
     */
    @TableId
    private Long id;

    /**
     * 用户名
     */
    private String uname;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 是否为管理员，1 是，0 否
     */
    private Boolean isAdmin;

    /**
     * 是否删除
     */
    private Boolean isDelete;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 是否过期
     */
    private Boolean accountnonexpired;

    /**
     * 是否锁定
     */
    private Boolean accountnonlocked;

    /**
     * 密码是否过期
     */
    private Boolean credentialsnonexpired;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private List<Role> roles;
}