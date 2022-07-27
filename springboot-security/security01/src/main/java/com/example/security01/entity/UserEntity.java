package com.example.security01.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author lishizhan
 * @email 1575234570@qq.com
 * @date 2022-07-22 16:17:58
 */
@Data
@TableName("user")
public class UserEntity {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     * 用户ID
     */
    private String username;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别：1表示男，0表示女
     */
    private String gender;
    /**
     * 地址
     */
    private String addr;
    /**
     * 介绍
     */
    private String intro;
    /**
     * 生日
     */
    private Date birthday;

    /*自动填充,不需要使用set的方式*/
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     *
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private String userImgUrl;


    /**
     * 用户状态：0：正常，2：锁定
     */
    private String status;


    @TableLogic
    private Integer deleted;


}
