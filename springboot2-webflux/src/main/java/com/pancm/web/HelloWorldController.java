package com.pancm.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
* Title: HelloWorldController
* Description: springboot 接口测试
* 首先启动 Application 程序，然后在浏览器输入http://localhost:8080//hello 
* 即可查看信息
* 在类中添加  @RestController, 默认类中的方法都会以json的格式返回
* Version:1.0.0  
* @author pancm
* @date 2019年4月11日
 */
@RestController
public class HelloWorldController {
	
    @RequestMapping("/hello")
    public String index() {
    	
        return "Hello World";
    }
    
     
 
    
}
