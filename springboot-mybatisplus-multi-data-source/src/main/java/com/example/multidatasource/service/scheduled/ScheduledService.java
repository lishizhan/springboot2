package com.example.multidatasource.service.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Author : lishizhan
 * @Date : 2022/8/22/0022
 * @email : 1575234570@qq.com
 * @Description : 定时任务
 */
@Service
public class ScheduledService {


    @Scheduled(cron = "0 * * * * MON-SAT ")
    public void testInsert(){
        System.out.println(" 每分钟执行一次 ");

    }



}
