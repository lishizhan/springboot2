package com.example.annotations.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Alishiz
 * @Date : 2022/7/7/0007
 * @email : 1575234570@qq.com
 * @Description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private String name;
    private Integer age;
}
