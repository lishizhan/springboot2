package com.example.springbootredis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class User implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    /**逻辑删除*/
    @TableLogic
    private Integer deleted;

    /**乐观锁
     * 取出记录时，获取当前 version
     * 更新时，带上这个 version
     * 执行更新时， set version = newVersion where version = oldVersion
     * 如果 version 不对，就更新失败
     * */
    @Version
    private Integer version;//版本号


    public User() {
    }

    public User(Long id, String name, Integer age, String email, Integer deleted) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.deleted = deleted;
    }

    public User(Long id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public User(Long id, String name, Integer age, String email, Integer deleted, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.deleted = deleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    /*自动填充,不需要使用set的方式*/
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
