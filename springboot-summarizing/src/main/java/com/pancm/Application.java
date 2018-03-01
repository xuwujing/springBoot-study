package com.pancm;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pancm.config.MyConfig;
/**
 * 
* Title: Application
* Description:
* springBoot 主程序 
* Version:1.0.0  
* @author pancm
* @date 2018年1月5日
 */

@SpringBootApplication
//mapper 接口类扫描包配置
@MapperScan("com.pancm.dao")
public class Application implements CommandLineRunner  {
    
	@Autowired  
    private MyConfig myconfig; 
	/*
	 *  SpringApplication 则是用于从main方法启动Spring应用的类。默认，它会执行以下步骤：
		1.创建一个合适的ApplicationContext实例 （取决于classpath）。
		2.注册一个CommandLinePropertySource，以便将命令行参数作为Spring properties。
		3.刷新application context，加载所有单例beans。
		4.激活所有CommandLineRunner beans。
	 */
	public static void main(String[] args) {
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(Application.class, args);
		System.out.println("程序正在运行...");
	}
	
	 @Override  
    public void run(String... strings) throws Exception {  
        System.out.println("配置文件:"+myconfig.getTest());  
    } 
}
