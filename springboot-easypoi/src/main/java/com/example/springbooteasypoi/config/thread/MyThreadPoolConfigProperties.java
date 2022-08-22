package com.example.springbooteasypoi.config.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author : Alishiz
 * @Date : 2022/4/4/0004
 * @email : 1575234570@qq.com
 * @Description :
 */
@ConfigurationProperties(prefix = "legou.thread")
@Component
@Data
public class MyThreadPoolConfigProperties {
    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime;
}
