package com.pancm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.pancm.dao.UserDao;
import com.pancm.pojo.User;
import com.pancm.service.UserService;

/**
 * 
 * @Title: UserServiceImpl 
 * @Description: 用户操作实现类
 * @Version:1.0.0
 * @author pancm
 * @date 2018年3月19日
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao udao;


	@Autowired
	private DataSourceTransactionManager dataSourceTransactionManager;
	@Autowired
	private TransactionDefinition transactionDefinition;
	
	@Override
	@Transactional
	public boolean test1(User user) throws Exception {
		/*
		 * 简单的事物回滚 由Spring框架进行回滚
		 */
		long id = user.getId();
		System.out.println("查询的数据1:" + udao.findById(id));
		// 新增两次，会出现主键ID冲突，看是否可以回滚该条数据
		udao.insert(user);
		System.out.println("查询的数据2:" + udao.findById(id));
		udao.insert(user);
		return false;
	}

	@Override
	@Transactional
	public boolean test2(User user) {

		/*
		 * 简单的事物回滚 自己捕获该异常进行手动回滚
		 */
		long id = user.getId();
		try {
			System.out.println("查询的数据1:" + udao.findById(id));
			// 新增两次，会出现主键ID冲突，看是否可以回滚该条数据
			udao.insert(user);
			System.out.println("查询的数据2:" + udao.findById(id));
			udao.insert(user);
		} catch (Exception e) {
			System.out.println("发生异常,进行手动回滚！");
			// 手动回滚事物
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			// 注意手动回滚事物要在异常抛出之前！
			e.printStackTrace();
		}

		return false;
	}

	@Override
	@Transactional
	public boolean test3(User user) {

		/*
		 * 子方法出现异常进行回滚
		 */
		try {
			System.out.println("查询的数据1:" + udao.findById(user.getId()));
			deal1(user);
			deal2(user);
		} catch (Exception e) {
			// 手动回滚事物
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		} 
		return false;

	}

	@Transactional(rollbackFor = Exception.class)
	public void deal1(User user) throws Exception {
		udao.insert(user);
		System.out.println("查询的数据2:" + udao.findById(user.getId()));
	}

	@Transactional(rollbackFor = Exception.class)
	public void deal2(User user) throws Exception {
		user.setAge(19);
		user.setName("xuwujing2");
		udao.update(user);
		System.out.println("查询的数据3:" + udao.findById(user.getId()));
		throw new Exception("手动模拟一个异常！");
	}

	@Override
	public boolean test4(User user) {
		/*
		 * 手动进行事物控制
		 */
		TransactionStatus transactionStatus=null;
		boolean isCommit = false;
		try {
			transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
			System.out.println("查询的数据1:" + udao.findById(user.getId()));
			// 进行新增/修改
			udao.insert(user);
			System.out.println("查询的数据2:" + udao.findById(user.getId()));
			if(user.getAge()<20) {
				user.setAge(user.getAge()+2);
				udao.update(user);
				System.out.println("查询的数据3:" + udao.findById(user.getId()));
			}else {
				throw new Exception("模拟一个异常!");
			}
			//手动提交
			dataSourceTransactionManager.commit(transactionStatus);
			isCommit= true;
			System.out.println("手动提交事物成功!");
			throw new Exception("模拟第二个异常!");

		} catch (Exception e) {
			//如果未提交就进行回滚
			if(!isCommit){
				System.out.println("发生异常,进行手动回滚！");
				//手动回滚事物
				dataSourceTransactionManager.rollback(transactionStatus);
			}

			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean test5(User user) {
		/*
		 * 调用其他service层的事物控制
		 */
		
		
		return false;
	}

}
