package com.pancm.service;

import java.util.List;

import com.pancm.pojo.User;


/**
 * 
* Title: UserService
* Description:用户接口 
* Version:1.0.0  
* @author pancm
* @date 2018年1月9日
 */
public interface UserService {
	
	
	/**
	 * 批量新增用户
	 * @param user
	 * @return
	 */
	boolean insertBatch(List<User> user);
	
	
	/**
	 * 查询用于
	 * @param user
	 * @return
	 */
	User findByUser(User user);
	
	
}
