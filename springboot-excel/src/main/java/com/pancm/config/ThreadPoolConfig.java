package com.pancm.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PreDestroy;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池
 */
@Configuration
@Slf4j
public class ThreadPoolConfig {

    @Value("${pcm.executor.thread.corePoolSize:10}")
    private int corePoolSize;
    @Value("${pcm.executor.thread.maxPoolSize:50}")
    private int maxPoolSize;
    @Value("${pcm.executor.thread.queueCapacity:10000}")
    private int queueCapacity;
    @Value("${pcm.executor.thread.keepAliveSeconds:60}")
    private int keepAliveSeconds;

    @Bean(name = "pcmTaskExecutor")
    public ThreadPoolTaskExecutor exportTaskExecutor(){
        log.info(".........pcm线程池开始启动.........");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(corePoolSize);
        // 最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        // 任务队列大小
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix("attendanceTaskExecutor-");
        // 直接拒绝异常
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 线程初始化
        executor.initialize();
        log.info(".........pcm线程池启动成功!corePoolSize#{}, maxPoolSize#{}, queueCapacity#{}", corePoolSize, maxPoolSize, queueCapacity);
        return executor;
    }

    @PreDestroy
    public void shutdownExecutor() {
        log.info(".........pcm线程池关闭.........");
        ThreadPoolTaskExecutor executor = exportTaskExecutor();
        executor.shutdown();
    }


}
