package com.pancm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	private static Logger LOG=LoggerFactory.getLogger(HelloWorldController.class);
	
    @RequestMapping("/hello")
    public String index() {
    	LOG.info("---------开始----------");
        return "Hello World";
    }
    
     
    @RequestMapping("/getUser")
    public User getUser() {
    	// 直接获取界面会直接返回 {"id":2,"name":"李四"}
    	LOG.info("---------开始----------");
    	User user=new User();
    	user.setId(2L);
    	user.setName("李四");
        return user;
    }
    
    
}
