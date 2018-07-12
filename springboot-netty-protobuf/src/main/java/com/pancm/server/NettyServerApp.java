package com.pancm.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
* @Title: NettyClientApp
* @Description: Netty 服务端主程序
* @Version:1.0.0  
* @author pancm
* @date 2018年7月11日
*/
@SpringBootApplication
public class NettyServerApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		ApplicationContext context = SpringApplication.run(NettyServerApp.class, args);
		NettyServer nettyServer = context.getBean(NettyServer.class);
		nettyServer.run();
	}

}
