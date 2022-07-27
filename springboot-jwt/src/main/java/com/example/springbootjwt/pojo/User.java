package com.example.springbootjwt.pojo;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Integer status;
    private String pwd;


}
