package com.example.springbooteasypoi.entity;

import java.util.Collections;
import java.util.Date;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.*;
import javafx.beans.binding.StringBinding;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2022-08-21 21:18:51
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
@ExcelTarget(value = "users") //将这个对象导出为excel,value为唯一表示
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    @Excel(name = "编号")
    private Long id;

    @Excel(name = "姓名")
    private String name;

    @Excel(name = "密码")
    @ExcelIgnore //排除导出
    private String password;

    @Excel(name = "年龄")
    private Integer age;
    //replace = {"佛山_demoData"} 将demoData替换为佛山
    @Excel(name = "地址", replace = {"佛山_demoData"})
    private String addr;

    @Excel(name = "手机号")
    private String phone;

    @Excel(name = "状态", replace = {"未删除_0", "未删除_1"})
    private String status;

    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创建时间", width = 35.0, format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "修改时间", width = 35.0, format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Excel(name = "时候为管理员")
    private String isAdmin;

    @Excel(name = "是否删除", replace = {"是_1", "否_0"})
    private String isDelete;

    @ExcelIgnore
    private String hobbyId;
    @ExcelIgnore
    @TableField(exist = false)
    private List<Hobby> hobbies;

    //将对对象集合中的某个属性以字符串的形式导出
    @TableField(exist = false)
    @Excel(name = "爱好", width = 20.0)
    private String hobbyStr;

    public String getHobbyStr() {
        StringBuilder h = new StringBuilder();
        if (!ObjectUtils.isEmpty(hobbies)) {
            hobbies.forEach(hobby -> {
                h.append(hobby.getHobbyName()).append("、");
            });
        }
        return h.toString();
    }

    private String cartId;

    @TableField(exist = false)
    @ExcelEntity //表示一对一
    private Card card;




}
