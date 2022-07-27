package com.example.mybatisplus;

import com.example.mybatisplus.dao.PersonDao;
import com.example.mybatisplus.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PersonTest {

    @Autowired
    private PersonDao personDao;
    //1. 实现一个通用的插入操作：支持动态插入，可以根据传入的字段的值，动态生成所需要的各种 insert语句

    @Test
    public void insertTest(){
        Person person = new Person();
        person.setId(null);
        person.setName("zhans");
        person.setAge(23);
        person.setSalary(new BigDecimal("8800"));
        int insert = personDao.insert(person);
        System.out.println("insert = " + insert);
        System.out.println("person = " + person);
    }


    //2. 批量插入功能

    @Test
    public void batchTest(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(null,"zhansgg",23,new BigDecimal("8888.99")));
        personList.add(new Person(null,"zhansgg",23,new BigDecimal("8888.99")));
        personList.add(new Person(null,"zhansgg",23,new BigDecimal("8888.99")));
        personList.add(new Person(null,"zhansgg",23,new BigDecimal("8888.99")));
        int i = personDao.batchInsert(personList);
        System.out.println("i = " + i);
    }
    //3. 实现一个通用的更新操作：支持动态更新操作，可以根据传入的字段，动态生成所需要的各种 update语句

    @Test
    public void updateTest(){
        Person person = new Person(14,null,null,new BigDecimal("100090.00"));
        int i = personDao.update(person);
        System.out.println("i = " + i);
    }

    //4. 实现一个通用的查询操作：支持各种组合条件查询、支撑排序、分页、支持返回列的控制等各种复杂的查询需求
}
