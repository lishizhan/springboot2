package com.example.security03.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色
 * @TableName role
 */
@Data
public class RoleVo implements Serializable {

    /**
     * 角色名称英文
     */
    private String name;

    /**
     * 角色 中文名称
     */
    private String nameZh;

}