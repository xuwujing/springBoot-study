package com.pancm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pancm.config.MyProperties;
import com.pancm.pojo.User;

/**
 * 
* Title: HelloWorldController
* Description: springboot 接口测试
* 首先启动 Application 程序，然后在浏览器输入http://localhost:8080//hello 
* 即可查看信息
* 在类中添加  @RestController, 默认类中的方法都会以json的格式返回
* Version:1.0.0  
* @author pancm
* @date 2018年1月3日
 */
@RestController
public class HelloWorldController {
	
	@Autowired
	MyProperties myProperties;
	
	//使用Environment类获取配置文件
	@Autowired
	Environment env;
	
    @RequestMapping("/hello")
    public String index() {
    	System.out.println("---------开始----------");
    	System.out.println("title:"+myProperties.getTitle());
    	System.out.println("describe:"+myProperties.getDescription());
    	System.out.println("server.port:"+env.getProperty("server.port"));
        return "Hello World";
    }
    
     
    @RequestMapping("/getUser")
    public User getUser() {
    	System.out.println("---------开始----------");
    	User user=new User();
    	user.setId(2);
    	user.setName("李四");
        return user;
    }
    
    
}
