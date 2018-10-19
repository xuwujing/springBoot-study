package com.pancm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pancm.pojo.User;
import com.pancm.service.UserService;



/**
 * 
* @Title: UserRestController
* @Description: 
*  用户控制层
* @Version:1.0.0  
* @author pancm
* @date 2018年3月19日
 */
@RestController
@RequestMapping(value = "/api")
public class UserRestController {
	
	private  final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
    private UserService userService;
 
	@PostMapping("/user")
    public boolean addUser(@RequestBody User user) {
    	logger.info("开始新增...");
        return userService.addUser(user);
    }
    
	@PutMapping("/user")
    public boolean updateUser(@RequestBody User user) {
    	logger.info("开始更新...");
        return userService.updateUser(user);
    }
	
	@DeleteMapping("/user")
    public boolean delete(@RequestParam(value = "id", required = true) int userId) {
    	logger.info("开始删除...");
        return userService.deleteUser(userId);
    }
	

    @GetMapping("/user")
    public User findByUserId(@RequestParam(value = "id", required = true) int userId) {
    	logger.info("开始查询所有数据...");
        return userService.findByUserId(userId);
    }
}
