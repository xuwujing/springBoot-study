package com.pancm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pancm.pojo.User;

/**
 * 
* Title: UserDao
* Description:
* 用户数据接口 
* Version:1.0.0  
* @author pancm
* @date 2018年1月9日
 */
@Mapper
public interface UserDao {
	
	/**
	 * 用户数据新增
	 */
	  void addUser(User user); 
	 
	 /**
	  * 用户数据修改
	  */
	  void updateUser(User user);

	 /**
	  * 用户数据删除
	 */
	 void deleteUser(int id);
	
	
    /**
     * 查询所有
     */   
    List<User> findAll();
    
}
