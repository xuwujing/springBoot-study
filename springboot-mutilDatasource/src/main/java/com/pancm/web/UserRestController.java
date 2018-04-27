package com.pancm.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@Autowired
    private UserService userService;
 
	@RequestMapping(value = "/user", method = RequestMethod.POST)
    public boolean insert(@RequestBody User user) {
    	System.out.println("开始新增...");
        return userService.insert(user);
    }
    
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
    public boolean update(@RequestBody User user) {
    	System.out.println("开始更新...");
        return userService.update(user);
    }
	
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public boolean delete(@RequestBody User user)  {
    	System.out.println("开始删除...");
        return userService.delete(user);
    }
	
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User findByUser(User user) {
    	System.out.println("开始查询...");
        return userService.findByEntity(user);
    }
    
    @RequestMapping(value = "/user/findAll", method = RequestMethod.GET)
    public List<User> findAll() {
    	System.out.println("开始查询所有用户");
        return userService.findAll();
    }
}
