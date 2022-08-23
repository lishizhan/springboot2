package org.example.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lishizhan
 * @description:
 * @date 2022/8/19 17:04
 */
@Getter
@Setter
public class Person {
    private String name;  // 姓名
    private int salary; // 薪资
    private int age; // 年龄
    private String sex; //性别
    private String area;  // 地区

    // 构造方法
    public Person(String name, int salary, int age, String sex, String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }
}