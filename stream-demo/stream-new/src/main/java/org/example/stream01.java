package org.example;

import org.example.pojo.Person;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lishizhan
 * @description:
 * @date 2022/8/19 17:04
 */
public class stream01 {
    public List<Person>  getPerson(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900 ,23, "male", "New York"));
        personList.add(new Person("Jack", 7000,24, "male", "Washington"));
        personList.add(new Person("Lily", 7800,22, "female", "Washington"));
        personList.add(new Person("Anni", 8200,21, "female", "New York"));
        personList.add(new Person("Owen", 9500,26, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 29,"female", "New York"));
        return personList;
    }

    //3.1 遍历/匹配（foreach/find/match）
    @Test
    public void test(){
        List<Integer> list  = Arrays.asList(1,2,41,4,24,123,124,12);
        //遍历符合条件的元素
        list.stream().filter(integer -> integer>20).forEach(System.out::println);
        //匹配第一个
        Optional<Integer> stream = list.stream().filter(integer -> integer > 20).findFirst();
        System.out.println("匹配第一个值："+stream.get());

    }
    //案例二：筛选员工中工资高于8000的人，并形成新的集合。
    @Test
    public void test01(){
        List<Person> persons = getPerson();


        List<String> names = persons.stream()
                .filter(person -> person.getSalary() > 8000)
                .map(person -> person.getName())
                .collect(Collectors.toList());
        System.out.println("names = " + names);
    }

    //聚合（max/min/count)
    @Test
    public void test02(){
        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        //「案例一：获取String集合中最长的元素。」
        Optional<String> max1 = list.stream().max(Comparator.comparingInt(String::length));
        System.out.println("max1 = " + max1.get());
    }


}
