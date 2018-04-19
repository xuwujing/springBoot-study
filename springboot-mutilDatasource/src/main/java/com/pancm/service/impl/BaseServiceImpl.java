package com.pancm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pancm.dao.BaseDao;
import com.pancm.service.BaseService;

/**
 * 
* @Title: BaseServiceImpl
* @Description:
* 数据层公共实现类 
* @Version:1.0.0  
* @author pancm
* @date 2018年4月12日
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	private static final Logger logger= LoggerFactory.getLogger(BaseServiceImpl.class);

	protected abstract BaseDao<T> getMapper();
	

	@Override
	public int insert(T entity)  {
		int t=-1;
		try {
			 t=getMapper().insert(entity);
		} catch (Exception e) {
			logger.error("新增"+entity.getClass()+"失败!原因是:",e);
		}
		return t;
	}
	
	
	@Override
	public int update(T entity){
		int t=-1;
		try {
			 t= getMapper().update(entity);
		} catch (Exception e) {
			logger.error("更新"+entity+"失败!原因是:",e);
		}
		return t;
	}
	
	@Override
	public int deleteByPrimaryKey(int id)  {
		int t=-1;
		try {
			 t= getMapper().deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.error("id:"+id+"删除失败!原因是:",e);
		}
		return t;
	}
	
	@Override
	public int delete(T entity){
		int t=-1;
		try {
			 t= getMapper().delete(entity);
		} catch (Exception e) {
			logger.error("删除"+entity+"失败!原因是:",e);
		}
		return t;
	}
	
	@Override
	public T findByPrimaryKey(int id) {
		T obj = null;
		try {
			obj = getMapper().findByPrimaryKey(id);
		} catch (Exception e) {
			logger.error("id:"+id+"查询失败!原因是:",e);
		}
		return obj;
	}
	
	@Override
	public T findByEntity(T entity) {
		T obj = null;
		try {
			obj = getMapper().findByEntity(entity);
		} catch (Exception e) {
			logger.error("查询"+entity.getClass().getName()+"失败!原因是:",e);
		}
		return obj;
	}
	
	@Override
	public List<T> findByListEntity(T entity) {
		List<T> list = null;
		try {
			list = getMapper().findByListEntity(entity);
		} catch (Exception e) {
			logger.error("查询"+entity.getClass().getName()+"失败!原因是:",e);
		}
		return list;
	}
	
	@Override
	public List<T> findAll() {
		List<T> list = null;
		try {
			list = getMapper().findAll();
		} catch (Exception e) {
			logger.error("查询失败!原因是:",e);
		}
		return list;
	}
	
	@Override
	public Object findByObject(Object obj) {
		Object result = null;
		try {
			result = getMapper().findByObject(obj);
		} catch (Exception e) {
			logger.error("查询"+obj+"失败!原因是:",e);
		}
		return result;
	}
	
	
}
