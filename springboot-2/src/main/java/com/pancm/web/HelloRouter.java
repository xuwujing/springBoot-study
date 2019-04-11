package com.pancm.web;

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
 * @Version:1.0.0
 * @Since: jdk1.8
 * @author pancm
 * @date 2019年4月11日
 */
@Configuration
public class HelloRouter {

	@Bean
	public RouterFunction<ServerResponse> routeCity(HelloHandler hander) {
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