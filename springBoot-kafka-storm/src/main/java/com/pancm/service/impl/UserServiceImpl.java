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
	public boolean insertBatch(List<User> user) {
		boolean flag=false;
		try {
			userDao.insertBatch(user);
			flag=true;
			System.out.println("批量新增"+user.size()+"条数据成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public User findByUser(User user) {
		return userDao.findByUser(user);
	}


}
