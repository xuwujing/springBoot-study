package com.pancm.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pancm.pojo.User;

/**
 * 
* Title: HelloWorld2
* Description: 
* @EnableAutoConfiguration 用于自动配置。
* 简单的说，它会根据你的pom配置（实际上应该是根据具体的依赖）来判断这是一个什么应用，并创建相应的环境。
* Version:1.0.0  
* @author pancm
* @date 2018年1月8日
 */

@Controller
@EnableAutoConfiguration
public class HelloWorld2 {
	 
	   
		@RequestMapping("/2")
	    @ResponseBody
	 	 public User getUser() {
	     	// 直接获取界面会直接返回 {"id":2,"name":"李四"}
	     	System.out.println("---------开始----------");
	     	User user=new User();
	     	user.setId(1L);
	     	user.setName("张三");
	         return user;
	     }
}
