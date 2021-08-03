package com.pancm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
/**
 * 
* Title: MyConfig
* Description:
* 自定义配置文件 
* Version:1.0.0  
* @author pancm
* @date 2018年1月20日
 */
@Component
public class MyConfig {

    @Value("${server.port:9863}")
    private String port;

    public String getPort() {
        return port;
    }
}
