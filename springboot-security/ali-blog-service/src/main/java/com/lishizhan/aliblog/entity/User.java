package com.lishizhan.aliblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户唯一ID
     */
    @TableId
    private Long id;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 账户是否过期
     */
    private Boolean accountNonExpired;

    /**
     * 账户是否被锁
     */
    private Boolean accountNonLocked;

    /**
     * 密码是否过期
     */
    private Boolean credentialsNonExpired;

    /**
     * 账户是否启用
     */
    private Boolean enabled;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户创建时间
     */
    private Date createTime;

    /**
     * 用户更新时间
     */
    private Date updateTime;

    /**
     * 用户个性签名
     */
    private String recommend;

    /**
     * 用户生日
     */
    private Date birthday;

    /**
     * 阿狸博客积分
     */
    private Integer aliScore;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}