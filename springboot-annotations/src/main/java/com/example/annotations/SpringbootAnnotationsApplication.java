package com.example.annotations;

import com.example.annotations.beans.Pet;
import com.example.annotations.beans.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class SpringbootAnnotationsApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringbootAnnotationsApplication.class, args);
        /*String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            log.info(name);
        }*/

        //条件装配 @Conditional
        /*boolean user = context.containsBean("user");
        System.out.println("容器中user组件：" + user);
        boolean tom = context.containsBean("tom");
        System.out.println("容器中tom组件：" + tom);*/

        //引入xml配置 @ImportResource("classpath:xxx/xxx.xml“)
        boolean user01 = context.containsBean("user01");
        System.out.println("user01 = " + user01);
        boolean tom01 = context.containsBean("tom01");
        System.out.println("tom01 = " + tom01);


    }

}
