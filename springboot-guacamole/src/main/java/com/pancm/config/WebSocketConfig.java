package com.pancm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
* @Title: WebSocketConfig
* @Description:
* @Version:1.0.0
* @Since:jdk1.8
* @author pancm
* @Date  2021/7/2
**/
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
