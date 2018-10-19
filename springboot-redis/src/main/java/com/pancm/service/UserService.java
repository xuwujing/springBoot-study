package com.pancm.service;

import com.pancm.pojo.User;


/**
 * 
* Title: UserService
* Description: 
* 用户接口 
* Version:1.0.0  
* @author pancm
* @date 2018年3月19日
 */
public interface UserService {
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	boolean addUser(User user);
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	boolean updateUser(User user);
	
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	boolean deleteUser(int id);
	

    /**
     * 查询所有
     * @return
     */
	User findByUserId(int id);
}
