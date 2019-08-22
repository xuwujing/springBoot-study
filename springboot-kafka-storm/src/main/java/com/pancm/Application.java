package com.pancm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.pancm.storm.TopologyApp;
import com.pancm.util.GetSpringBean;
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
public class Application{

	public static void main(String[] args) {
		//先启动Storm，后启动springboot的工程
		TopologyApp app = new TopologyApp();
		app.runStorm(args);
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		GetSpringBean springBean=new GetSpringBean();
		springBean.setApplicationContext(context);

	}
	
}
