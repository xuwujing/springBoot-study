package com.pancm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;



/**
 * 
 * @Title: Application
 * @Description: springBoot task主程序
 	 异步调用测试 EnableAsync: 开启异步调用，异步调用的方法只需加上@Async注解即可，
         一般放到启动类中，需要注意的是@Async所修饰的函数不要定义为static类型，这样异步调用不会生效
 * @Version:1.0.0
 * @author pancm
 * @date 2018年1月5日
 */
@EnableAsync
@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	/*
	 * SpringApplication 则是用于从main方法启动Spring应用的类。默认，它会执行以下步骤：
	 * 1.创建一个合适的ApplicationContext实例 （取决于classpath）。
	 * 2.注册一个CommandLinePropertySource，以便将命令行参数作为Spring properties。 3.刷新application
	 * context，加载所有单例beans。 4.激活所有CommandLineRunner beans。
	 */
	public static void main(String[] args) {
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(Application.class, args);
		logger.info("程序启动成功!");
	}

	
}
