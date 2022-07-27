package com.example.springboottest;

import com.example.springboottest.bean.Car;
import com.example.springboottest.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

//@SpringBootApplication(scanBasePackages ="com.example.test" )
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com.example")
@ImportResource("classpath:beans/beans.xml")

public class SpringbootTestApplication {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = SpringApplication.run(SpringbootTestApplication.class, args);
        User user01 = (User) context.getBean("user01");
        User user02 = (User) context.getBean("user02");
        System.out.println("user01 = " + user01);
        System.out.println("user02 = " + user02);

        //配置绑定
//        getProperties();

        Car car = context.getBean(Car.class);
        System.out.println("car = " + car);


    }

    public static void main01(String[] args) {
        //1，直接返回IOC 容器
        ApplicationContext context = SpringApplication.run(SpringbootTestApplication.class, args);

        //2，查看容器中的所有组件
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        //3，判断组件中默认是不是单实例
       /*
        User tom01 = context.getBean("tom", User.class);
        User tom02 = context.getBean("tom", User.class);
        System.out.println(tom01 == tom02);
        */

        //4，配置类本身也是一个组件
        /*MyConfig myConfig = context.getBean(MyConfig.class);
        User user01 = myConfig.user();
        User user02 = myConfig.user();
        System.out.println(user01 == user02);*/

        //重复获取容器中的对象
        /*Person person01 = context.getBean("person01", Person.class);
        Person person02 = context.getBean("person01", Person.class);
        System.out.println("重复获取容器中的对象是否相等？===>" + (person01 == person02));
        System.out.println("-------------------------------------------------------");
        FullAndLifeConfiguration bean = context.getBean(FullAndLifeConfiguration.class);
        Person person03 = bean.person01();
        Person person04 = bean.person01();
        System.out.println("重复获取配置类组件中的对象是否相等？===>" + (person03 == person04));*/

    }

   /* public static void test01(ApplicationContext context) {
        Person person01 = context.getBean("person01", Person.class);
        Person person02 = context.getBean("person01", Person.class);
        System.out.println("重复获取容器中的对象是否相等？===>" + (person01 == person02));
    }
    public static void test02(ApplicationContext context) {
        FullAndLifeConfiguration bean = context.getBean(FullAndLifeConfiguration.class);
        System.out.println("bean = " + bean);
        *//*Person person01 = bean.person01();
        Person person02 = bean.person01();
        System.out.println("重复获取容器中的对象是否相等？===>" + (person01 == person02));*//*
    }
    */

    /**
     * 获取properties文件中的数据
     */
    public static void getProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("springboot-test\\src\\main\\resources\\test.properties"));
        //获取配置文件名字
        Enumeration<?> names = properties.propertyNames();
        while (names.hasMoreElements()) {
            String key = (String) names.nextElement();
            String value = properties.getProperty(key);
            System.out.println(key + " = " + value);
        }
    }
}
