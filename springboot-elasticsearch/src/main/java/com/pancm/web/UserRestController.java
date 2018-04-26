package com.pancm.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pancm.dao.UserDao;
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
	private UserDao userDao;
	
    @PostMapping("/user")
    public boolean createUser(@RequestBody User user) {
        return userService.insert(user);
    }

    
    @GetMapping("/user/content")
    public List<User> search(@RequestParam(value = "content") String content) {
		return userService.searchUser(content);
    }
    
    
    @GetMapping("/user")
    public List<User> searchUser(@RequestParam(value = "pageNumber") Integer pageNumber,
                                 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(value = "searchContent") String searchContent) {
        return userService.searchUser(pageNumber, pageSize, searchContent);
    }
    
    
    @GetMapping("/user2")
    public List<User> searchUserByWeight(@RequestParam(value = "pageNumber") Integer pageNumber,
                                 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(value = "searchContent") String searchContent) {
     return userService.searchUserByWeight(pageNumber, pageSize, searchContent);
    }
    
    
}
