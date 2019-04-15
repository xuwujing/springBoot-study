package com.pancm.web;

import com.pancm.handler.HelloHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 
 * @Title: HelloRouter
 * @Description:请求路由处理类
 主要 提供接口调用，类似  @RequestMapping 注解，可以理解为mvc中的controller
启动程序之后,在浏览器输入:http://localhost:8280/webflux/hello 即可查看返回信息
在浏览器输入:http://localhost:8280/actuator 即可查看程序的相关信息

springboot2.x的配置说明:https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Migration-Guide
 * @Version:1.0.0
 * @Since: jdk1.8
 * @author pancm
 * @date 2019年4月11日
 */
@Configuration
public class HelloRouter {

	@Bean
	public RouterFunction<ServerResponse> route(HelloHandler hander) {
		/**
		 * RouterFunctions:对请求路由处理类，即将请求路由到处理器。
		 * RouterFunctions.route(RequestPredicate, HandlerFunction) 方法，对应的入参是请求参数和处理函数，如果请求匹配，就调用对应的处理器函数。
		 * RequestPredicates.GET: 是将一个 GET 请求 /webflux/hello 路由到处理器 helloHandler 的 hello 方法上。跟 Spring MVC 模式下的 HandleMapping 的作用类似。
		
		 */
		
		RouterFunction<ServerResponse> routerFunction = RouterFunctions.route(
				RequestPredicates.GET("/webflux/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
				hander::hello);

		return routerFunction;
	}

}