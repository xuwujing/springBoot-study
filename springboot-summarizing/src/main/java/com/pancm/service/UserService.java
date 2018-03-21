package com.pancm.service;

import java.util.List;

import com.pancm.bean.User;


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
     * 根据用户名字查询用户信息
     * @param userName
     */
	User findUserByName(String userName);
    
    /**
     * 查询所有
     * 
     */
	List<User> findAll();

}
