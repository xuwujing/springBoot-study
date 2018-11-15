package com.pancm.task;

import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfigration {

    @Bean(name = "jobDetail")  
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduleTask task) {
        // ScheduleTask为需要执行的任务  
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();  
        /* 
         *  是否并发执行 
         *  例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了， 
         *  如果此处为true，则下一个任务会bing执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行 
         */  
        jobDetail.setConcurrent(true);  

        jobDetail.setName("scheduler");// 设置任务的名字  
        jobDetail.setGroup("scheduler_group");// 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用  

        /* 
         * 这两行代码表示执行task对象中的scheduleTest方法。定时执行的逻辑都在scheduleTest。
         */  
        jobDetail.setTargetObject(task);  

        jobDetail.setTargetMethod("scheduleTest");  
        return jobDetail;  
    } 

    @Bean(name = "jobTrigger")  
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean jobDetail) {  
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();  
        tigger.setJobDetail(jobDetail.getObject());  
//        tigger.setCronExpression("0/30 * * * * ?");// 表示每隔30秒钟执行一次
        tigger.setCronExpression("1/30 * * * * ?");// 表示每隔30秒钟执行一次
        //tigger.set
        tigger.setName("myTigger");// trigger的name  
        return tigger;  

    }

    @Bean(name = "scheduler")  
    public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger) {  
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        //设置是否任意一个已定义的Job会覆盖现在的Job。默认为false，即已定义的Job不会覆盖现有的Job。
        bean.setOverwriteExistingJobs(true);  
        // 延时启动，应用启动5秒后  ，定时器才开始启动
        bean.setStartupDelay(5);  
        // 注册定时触发器  
        bean.setTriggers(cronJobTrigger);  
        return bean;  
    }
    //多任务时的Scheduler，动态设置Trigger。一个SchedulerFactoryBean可能会有多个Trigger
    @Bean(name = "multitaskScheduler") 
    public SchedulerFactoryBean schedulerFactoryBean(){  
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();   
        return schedulerFactoryBean;   
    }
}
