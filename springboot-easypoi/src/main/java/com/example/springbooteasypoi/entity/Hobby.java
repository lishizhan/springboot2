package com.example.springbooteasypoi.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Hobby)表实体类
 *
 * @author makejava
 * @since 2022-08-21 22:36:09
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("hobby")
public class Hobby  {
    @TableId
    private Long id;

    
    private String hobbyName;



}
