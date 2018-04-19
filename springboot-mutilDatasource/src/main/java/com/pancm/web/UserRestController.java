package com.pancm.web;

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
	@Autowired
    private UserService userService;
 
	@RequestMapping(value = "/user", method = RequestMethod.POST)
    public int insert(@RequestBody User user) {
    	System.out.println("开始新增...");
    	int i=-1;
        try {
        	i=userService.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return i;
    }
    
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
    public int update(@RequestBody User user) throws Exception {
    	System.out.println("开始更新...");
        return userService.update(user);
    }
	
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public int delete(@RequestParam(value = "userId", required = true) int userId) throws Exception {
    	System.out.println("开始删除...");
        return userService.deleteByPrimaryKey(userId);
    }
	
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User findByUserName(User user) {
    	System.out.println("开始查询...");
        return userService.findByEntity(user);
    }
    
}
