package com.example.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 资产关联表
 * @TableName mgt_assets_relation
 */
@TableName(value ="mgt_assets_relation")
@Data
public class AssetsRelation implements Serializable {
    /**
     * 璧勪骇鍏宠仈id
     */
    @TableId
    private String id;

    /**
     * 璧勪骇锛堣澶囷級缂栧彿
     */
    private String facCode;
    @TableField(exist = false)
    private String facName;

    /**
     * 关联资产编号（设备1，设备2，设备2，...）
     */
    private String facRelation;

    @TableField(exist = false)
    private String facRelationNames;

    /**
     * 鎵€灞炵珯鐐笽D
     */
    private String stationCode;

    /**
     * 是否删除
     */
    private Boolean isDelete;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 修改者
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}