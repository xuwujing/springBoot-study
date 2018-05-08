package com.pancm.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.storm.shade.org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User findByUserName(@RequestParam User user) {
    	System.out.println("开始查询...");
        return userService.findByUser(user);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public boolean findByUserName(@RequestParam(value = "count", required = true) int count) {
    	List<String> list=new ArrayList<String>();
    	for(int i=0;i<count;i++){
    	 	User user=new User();
    	 	user.setName("张三"+i);
    	 	user.setAge(i);
    	 	list.add(JSON.toString(user));
    	}
    	KafkaProducerUtil.sendMessage(list, app.getServers(), app.getTopicName());
        return true;
    }
}
