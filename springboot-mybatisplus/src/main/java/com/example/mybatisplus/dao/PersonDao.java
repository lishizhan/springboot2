package com.example.mybatisplus.dao;

import com.example.mybatisplus.entity.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 1. 实现一个通用的插入操作：支持动态插入，可以根据传入的字段的值，动态生成所需要的各种
 * insert语句
 * 2. 批量插入功能
 * 3. 实现一个通用的更新操作：支持动态更新操作，可以根据传入的字段，动态生成所需要的各种
 * update语句
 * 4. 实现一个通用的查询操作：支持各种组合条件查询、支撑排序、分页、支持返回列的控制等各种复
 * 杂的查询需求
 */
@Mapper
public interface PersonDao {
    //1. 实现一个通用的插入操作：支持动态插入，可以根据传入的字段的值，动态生成所需要的各种 insert语句
    int insert(Person person);

    //2. 批量插入功能
    int batchInsert(List<Person> personList);
    //3. 实现一个通用的更新操作：支持动态更新操作，可以根据传入的字段，动态生成所需要的各种 update语句
    int update(Person person);



    //4. 实现一个通用的查询操作：支持各种组合条件查询、支撑排序、分页、支持返回列的控制等各种复杂的查询需求


}
