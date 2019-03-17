package com.pancm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @Title: Application
 * @Description: springBoot 主程序
 * @Version:1.0.0
 * @author pancm
 * @date 2018年1月5日
 */

@SpringBootApplication
public class SwaggerApplication  {

	private static final Logger logger = LoggerFactory.getLogger(SwaggerApplication.class);

	/*
	 * SpringApplication 则是用于从main方法启动Spring应用的类。默认，它会执行以下步骤：
	 * 1.创建一个合适的ApplicationContext实例 （取决于classpath）。
	 * 2.注册一个CommandLinePropertySource，以便将命令行参数作为Spring properties。 3.刷新application
	 * context，加载所有单例beans。 4.激活所有CommandLineRunner beans。
	 */
	public static void main(String[] args) {
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(SwaggerApplication.class, args);
		logger.info("Swagger程序启动成功!");
	}
}
