package com.pancm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pancm.bean.User;
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
@RequestMapping(value = "/api/user")
public class UserRestController {
	@Autowired
    private UserService userService;
 
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public boolean addUser( User user) {
    	System.out.println("开始新增...");
        return userService.addUser(user);
    }
    
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public boolean updateUser( User user) {
    	System.out.println("开始更新...");
        return userService.updateUser(user);
    }
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public boolean delete(@RequestParam(value = "userId", required = true) int userId) {
    	System.out.println("开始删除...");
        return userService.deleteUser(userId);
    }
	
    @RequestMapping(value = "/userName", method = RequestMethod.GET)
    public User findByUserName(@RequestParam(value = "userName", required = true) String userName) {
    	System.out.println("开始查询...");
        return userService.findUserByName(userName);
    }
    
    @RequestMapping(value = "/userId", method = RequestMethod.GET)
    public User findByUserId(@RequestParam(value = "userId", required = true) int userId) {
    	System.out.println("开始查询...");
        return userService.findUserById(userId);
    }
    
    @RequestMapping(value = "/userAge", method = RequestMethod.GET)
    public User findByUserAge(@RequestParam(value = "userAge", required = true) int userAge) {
    	System.out.println("开始查询...");
        return userService.findUserByAge(userAge);
    }
}
