package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        List<Stu> list = new ArrayList<>();
        list.add(new Stu("zhans",23));
        list.add(new Stu("zhans2",22));
        list.add(new Stu("zhans3",24));
        list.add(new Stu("zhans4",25));
        list.add(new Stu("zhans5",23));
        int pageNUm = 3;
        int size = 3;

        int pp = pageNUm-1;

        List page = toPage(pp, size, list);
        page.forEach(o -> {
            Stu stu = (Stu) o;
            System.out.println("o = " + stu);
        });


    }
    public static List toPage(int page, int size , List list) {
        int fromIndex = page * size;
        int toIndex = page * size + size;
        if(fromIndex > list.size()){
            return new ArrayList();
        } else if(toIndex >= list.size()) {
            return list.subList(fromIndex,list.size());
        } else {
            return list.subList(fromIndex,toIndex);
        }
    }





}
class Stu{
    private String name;
    private Integer age;

    public Stu() {
    }

    public Stu(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
