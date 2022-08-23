package com.example.dume.dataserver1.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dume
 * @create 2021-12-24 15:50
 **/
public class MainTable implements Serializable {
    private String id;
    private String heroname;
    private int age;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date operationtime;
    private String changedbname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeroname() {
        return heroname;
    }

    public void setHeroname(String heroname) {
        this.heroname = heroname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(Date operationtime) {
        this.operationtime = operationtime;
    }

    public String getChangedbname() {
        return changedbname;
    }

    public void setChangedbname(String changedbname) {
        this.changedbname = changedbname;
    }
}
