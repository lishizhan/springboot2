package com.example.springboottest.config;

import com.example.springboottest.bean.Animal;
import com.example.springboottest.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author : Alishiz
 * @Date : 2022/6/7/0007
 * @email : 1575234570@qq.com
 * @Description :
 */

/**
 * 1，配置类中使用@Bean标注在方法上给容器注册组件，默认是单实例的
 * 2，配置类本身也是组件
 * 3，proxyBeanMethods：代理bean的方法
 *     Full: proxyBeanMethods=true：表示外界不管怎样调用配置类中的组件都是从容器中调用
 *     Lite: proxuBeanMethods=false: 表示每个@Bean方法被调用多少次返回的组件都是新创建的
 * 4，@Import({User.class,DBHelper.class})：给容器中自动创建两个类型的组件，默认组件的名字是全类名
 *
 *
 *
 */

//@Import({Animal.class,User.class}) //给容器中自动创建出这两个类型的组件、默认组件的名字就是全类名
//告诉Spring Boot 这是一个配置类
@Configuration(proxyBeanMethods = true)
public class MyConfig {

    @Bean //给容器中添加组件。以方法名作为组件的id。返回类型就是组件类型。返回的值，就是组件在容器中的实例
    public User user(){
        return new User("001","zhans");
    }

    @Bean("tom")
    public User user01(){
        return new User("002","Tom");
    }

}
