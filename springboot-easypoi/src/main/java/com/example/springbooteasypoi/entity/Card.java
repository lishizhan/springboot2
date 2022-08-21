package com.example.springbooteasypoi.entity;

import java.util.Date;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Card)表实体类
 *
 * @author makejava
 * @since 2022-08-21 23:44:02
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("card")
@ExcelTarget(value = "cart")
public class Card  {
    @TableId
    @Excel(name = "编号")
    private Long id;
    //身份证号
    @Excel(name = "身份证号",width = 40.0)
    private String idNumber;
    //姓名
    @Excel(name = "姓名")
    private String name;
    //性别
    @Excel(name = "性别")
    private String sex;
    //出生日期
    @Excel(name = "出生日期")
    private Date birthday;
    //居住地址
    @Excel(name = "居住地址")
    private String addr;
    //身份证类型
    @Excel(name = "身份证类型")
    private String type;
    //机关
    @Excel(name = "机关")
    private String issuing;
}
