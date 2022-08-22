package com.example.springbooteasypoi.config.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author : Alishiz
 * @Date : 2022/4/4/0004
 * @email : 1575234570@qq.com
 * @Description :
 */
//@EnableConfigurationProperties(MyThreadPoolConfigProperties.class),因其配置文件已经加载到容器中，所以不需要
@Configuration
public class MyThreadPoolConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(MyThreadPoolConfigProperties properties) {
        /*
            int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue,
            RejectedExecutionHandler handler
        * */
        return new ThreadPoolExecutor(
                properties.getCorePoolSize(),//核心线程数量
                properties.getMaximumPoolSize(),//最大线程数量
                properties.getKeepAliveTime(),
                TimeUnit.SECONDS,//等待时间
                new LinkedBlockingDeque<>(100000), //最多阻塞10万个
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());//拒绝策略
    }


}
