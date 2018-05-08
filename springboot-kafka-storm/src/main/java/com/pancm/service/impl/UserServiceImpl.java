package com.pancm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
* 需要序列化
* Version:1.0.0  
* @author pancm
* @date 2018年1月9日
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
    private UserDao userDao;
	

	@Override
	public boolean insertBatch(List<User> user) {
		boolean flag=false;
		try {
			flag=userDao.insertBatch(user);
			logger.info("批量新增"+user.size()+"条数据成功!");
		} catch (Exception e) {
			logger.error("批量新增失败!数据:{},原因是:",e);
		}
		return flag;
	}

	@Override
	public User findByUser(User user) {
		return userDao.findByUser(user);
	}


}
