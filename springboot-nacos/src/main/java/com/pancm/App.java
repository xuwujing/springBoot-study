package com.pancm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @Author pancm
 * @Description springboot集成nacos配置中心
 * @Date  2024/1/26
 * @Param
 * @return
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class App 
{
	private static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main( String[] args )
    {
    	// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(App.class, args);
		logger.info("程序启动成功!");
    }
}
