package com.pancm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pancm.dao.BaseDao;
import com.pancm.service.BaseService;

//用abstract可以自定义添加一些公共方法
public abstract class BaseServiceImp<T> implements BaseService<T> {
	private static final Logger logger= LoggerFactory.getLogger(BaseServiceImp.class);

	protected abstract BaseDao<T> getMapper();
	
	@Override
	public int insert(T entity) throws Exception {
		int result = getMapper().insert(entity);
		return result;
	}
	
	
	@Override
	public int updateByPrimaryKey(T entity) throws Exception {
		int result = getMapper().updateByPrimaryKey(entity);
		return result;
	}
	
	@Override
	public int deleteByPrimaryKey(int id) throws Exception {
		int result = getMapper().deleteByPrimaryKey(id);
		return result;
	}
	
	@Override
	public int delete(T entity) throws Exception {
		int result = getMapper().delete(entity);
		return result;
	}
	
	@Override
	public T selectByPrimaryKey(int id) {
		T obj = null;
		try {
			System.out.println(id);
			obj = getMapper().selectByPrimaryKey(id);
			System.out.println(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return obj;
	}
	
	@Override
	public T getOne(T entity) {
		T obj = null;
		try {
			System.out.println("entity--"+entity);
			obj = getMapper().getOne(entity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return obj;
	}
	
	@Override
	public Object getObject(Object obj) {
		Object result = null;
		try {
			result = getMapper().getObject(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	
	
}
