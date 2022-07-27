package com.example.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@MapperScan(basePackages = "com.example.mybatisplus.dao")
public class MybatisPlusConfig {

    /**
     * mybatis-plus乐观锁配置
     * 乐观锁插件
     * @return
     */
/*    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }*/


    /**
     * 配置分页插件
     * */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    /**
     * SQL 执行性能分析插件
     * 开发环境中使用，线上不推荐，maxTime指的是sql最大执行时间
     */
    //....

}
