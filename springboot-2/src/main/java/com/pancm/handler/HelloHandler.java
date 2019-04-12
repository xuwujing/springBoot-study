package com.pancm.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

/**
 * 
* @Title: HelloHandler
* @Description: webflux 的功能实现类
   实现接口的功能，可以理解为mvc中的Service处理
  
* @Version:1.0.0  
* @Since: jdk1.8
* @author pancm
* @date 2019年4月11日
 */
@Configuration
public class HelloHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
    	
    	String hello="Hello, world!";
    	Mono<ServerResponse> mono = ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(BodyInserters.fromObject(hello));
        return mono;
    }
}