package com.pancm.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pancm.pojo.User;
import com.pancm.service.UserService;



/**
 * 
* Title: UserRestController
* Description: 
* 用户控制层
* Version:1.0.0  
* @author pancm
* @date 2018年3月19日
 */
@RestController
@RequestMapping(value = "/api")
public class UserRestController {
	
	private  final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
    private UserService userService;
 
	@RequestMapping(value = "/user", method = RequestMethod.POST)
    public boolean addUser(@RequestBody User user) {
    	logger.info("开始新增...");
        return userService.addUser(user);
    }
    
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody User user) {
    	logger.info("开始更新...");
        return userService.updateUser(user);
    }
	
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public boolean delete(@RequestParam(value = "userId", required = true) int userId) {
    	logger.info("开始删除...");
        return userService.deleteUser(userId);
    }
	

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> findByUserAge() {
    	logger.info("开始查询所有数据...");
        return userService.findAll();
    }
}
