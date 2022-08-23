package com.example.springbooteasypoi.entity.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

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
@ExcelTarget(value = "cartDTO")
public class CardDTO implements Serializable {
    //身份证号
    @Excel(name = "身份证号")
    private String idNumber;
    //姓名
    @Excel(name = "姓名")
    private String name;
    //性别
    @Excel(name = "性别")
    private String sex;
    //出生日期
    @Excel(name = "出生日期",isImportField = "true", importFormat =  "yyyy-MM-dd" ,databaseFormat = "yyyy-MM-dd")
    private Date birthday;
    //居住地址
    @Excel(name = "居住地址")
    private String addr;
    //身份证类型
    @Excel(name = "身份证类型")
    private String type;
    //机关
    @Excel(name = "签发机关")
    private String issuing;
}
