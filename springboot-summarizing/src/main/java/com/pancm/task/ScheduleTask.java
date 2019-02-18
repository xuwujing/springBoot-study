package com.pancm.task;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
 * 时间设置
 */
@Configuration
@EnableScheduling // 该注解必须要加
public class ScheduleTask { 
     public void scheduleTest() {
         System.out.println("scheduleTest开始定时执行!"+new Date());
    }
}
