package com.pancm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pancm.dao.UserDao;
import com.pancm.pojo.User;
import com.pancm.service.UserService;

/**
 * 
* Title: UserServiceImpl
* Description:
* 用户操作实现类 
* Version:1.0.0  
* @author pancm
* @date 2018年1月9日
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UserDao userDao;
	
	
	@Override
	public boolean addUser(User user) {
		boolean flag=false;
		try{
			userDao.addUser(user);
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateUser(User user) {
		boolean flag=false;
		try{
			userDao.updateUser(user);
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteUser(int id) {
		boolean flag=false;
		try{
			userDao.deleteUser(id);
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public User findUserByName(String userName) {
		return userDao.findByName(userName);
	}

	@Override
	public User findUserById(int userId) {
		return userDao.findById(userId);
	}

	@Override
	public User findUserByAge(int userAge) {
		return userDao.findByAge(userAge);
	}

	@Override
	public User findUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserByAll() {
		return userDao.findAll();
	}


}
