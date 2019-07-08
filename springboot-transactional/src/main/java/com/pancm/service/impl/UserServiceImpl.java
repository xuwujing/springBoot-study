package com.pancm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
* @date 2018年3月19日
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao udao;


	@Override
	@Transactional
	public boolean test1() throws Exception {
		/*
		 * 简单的事物回滚
		 * 由Spring框架进行回滚
		 */
		long id =1;
		User user = new User();
		user.setId(id);
		user.setAge(18);
		user.setName("xuwujing");
	
		try {
		    
			System.out.println("查询的数据1:"+udao.findById(id)); 
			//新增两次，会出现主键ID冲突，看是否可以回滚该条数据			
			udao.insert(user);
			System.out.println("查询的数据2:"+udao.findById(id)); 
			udao.insert(user);
		} finally{
			System.out.println("查询的数据3:"+udao.findById(id)); 
		}

		return false;
	}

	@Override
	public boolean test2() {
		
		/*
		 * 简单的事物回滚
		 * 自己捕获该异常进行手动回滚
		 */
		long id =1;
		User user = new User();
		user.setId(id);
		user.setAge(18);
		user.setName("xuwujing");
		try {
			System.out.println("查询的数据1:"+udao.findById(id)); 
			//新增两次，会出现主键ID冲突，看是否可以回滚该条数据			
			udao.insert(user);
			System.out.println("查询的数据2:"+udao.findById(id)); 
			udao.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
			//手动回滚事物
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();		
		}finally{
			System.out.println("查询的数据3:"+udao.findById(id)); 
		}
	
		return false;
	}
	
	
	


	

	@Override
	@Transactional
	public boolean test3() {
		
		/*
		 * 子方法出现异常进行回滚
		 */
		
		try {
			deal1();	
			deal2();			
		} catch (Exception e) {
			e.printStackTrace();
			//手动回滚事物
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();	
		}finally{
			System.out.println("查询的数据3:"+udao.findById(2)); 
		}
				
		return false;
		
		
	}

	@Transactional(rollbackFor = Exception.class)
	private void deal1() throws Exception {
		User user = new User();
		user.setId(2L);
		user.setAge(18);
		user.setName("xuwujing");
		udao.insert(user);
		System.out.println("查询的数据1:"+udao.findById(1)); 
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	private void deal2() throws Exception {
		User user = new User();
		user.setId(2L);
		user.setAge(19);
		user.setName("xuwujing2");
		udao.update(user);
		System.out.println("查询的数据2:"+udao.findById(1)); 
		throw new Exception("手动模拟一个异常！");
		
	}
	
	@Override
	public boolean test4() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean test5() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
}
