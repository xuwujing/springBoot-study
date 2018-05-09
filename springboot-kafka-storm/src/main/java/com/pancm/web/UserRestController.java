package com.pancm.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pancm.config.ApplicationConfiguration;
import com.pancm.kafka.KafkaProducerUtil;
import com.pancm.pojo.User;
import com.pancm.service.UserService;


/**
 * 
* Title: UserRestController
* Description: 
* 用户数据操作接口
* Version:1.0.0  
* @author pancm
* @date 2018年1月9日
 */
@RestController
@RequestMapping(value = "/api")
public class UserRestController {
	@Autowired
    private UserService userService;
	@Autowired
	ApplicationConfiguration app;
	
    @GetMapping("/user")
    public User findByUserName(@RequestParam User user) {
    	System.out.println("开始查询...");
        return userService.findByUser(user);
    }
    
    @PostMapping("/user")
    public boolean findByUserName(@RequestParam(value = "count", required = true) int count) {
    	List<String> list=new ArrayList<String>();
    	for(int i=1;i<=count;i++){
    	 	User user=new User();
    	 	user.setName("张三"+i);
    	 	user.setAge(i);
    	 	list.add(user.toString());
    	}
        return KafkaProducerUtil.sendMessage(list, app.getServers(), app.getTopicName());
    }
}
