package com.example.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName mgt_fac_inst_info
 */
@TableName(value ="mgt_fac_inst_info")
@Data
public class FacInstInfo implements Serializable {
    /**
     * 编码
     */
    @TableId
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型(永久长期等)
     */
    private String type;

    /**
     * 分类：1：设施 2：设备
     */
    private Integer sort;

    /**
     * 英文名称
     */
    private String englishName;

    /**
     * 型号
     */
    private String modelNumber;

    /**
     * 功能用途
     */
    private String purpose;

    /**
     * 主要技术指标
     */
    private String technicalIndex;

    /**
     * 监测类型
     */
    private String monitorType;

    /**
     * 站点code
     */
    private String stationCode;

    /**
     * 站点名称
     */
    private String stationName;

    /**
     * 场地code
     */
    private String fieldCode;

    /**
     * 场地名称
     */
    private String fieldName;

    /**
     * 样地code
     */
    private String plotCode;

    /**
     * 样地名称
     */
    private String plotName;

    /**
     * 状态：1：未激活 2：在线 3：离线
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date establishTime;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 更新日期
     */
    private Date updateTime;

    /**
     * 如果为设备，那么为此设备新建工单时，将会更新此字段，如果此字段为null，表明还没有此设备的相关工单
     */
    private Date lastTime;

    /**
     * 新建工单时，将此字段加一
     */
    private Integer maiCount;

    /**
     * 上次的工单添加时间
     */
    private Date preTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}