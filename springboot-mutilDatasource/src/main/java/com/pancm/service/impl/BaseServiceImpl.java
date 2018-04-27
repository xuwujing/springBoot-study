package com.pancm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pancm.dao.BaseDao;
import com.pancm.pojo.Student;
import com.pancm.pojo.User;
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
	public boolean insert(T entity)  {
		boolean falg=false;
		try {
			getMapper().insert(entity);
			falg=true;
		} catch (Exception e) {
			logger.error("新增"+getClassName(entity)+"失败!原因是:",e);
		}
		return falg;
	}
	
	
	@Override
	public boolean update(T entity){
		boolean falg=false;
		try {
			 getMapper().update(entity);
			 falg=true;
		} catch (Exception e) {
			logger.error("更新"+getClassName(entity)+"失败!原因是:",e);
		}
		return falg;
	}
	
	@Override
	public boolean deleteByPrimaryKey(int id)  {
		boolean falg=false;
		try {
			 getMapper().deleteByPrimaryKey(id);
			 falg=true;
		} catch (Exception e) {
			logger.error("id:"+id+"删除失败!原因是:",e);
		}
		return falg;
	}
	
	@Override
	public boolean delete(T entity){
		boolean falg=false;
		try {
			getMapper().delete(entity);
			falg=true;
		} catch (Exception e) {
			logger.error("删除"+getClassName(entity)+"失败!原因是:",e);
		}
		return falg;
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
			logger.error("查询"+getClassName(entity)+"失败!原因是:",e);
		}
		return obj;
	}
	
	@Override
	public List<T> findByListEntity(T entity) {
		List<T> list = null;
		try {
			Page<?> page =PageHelper.startPage(1,2); 
			System.out.println(getClassName(entity)+"设置第一页两条数据!");
			list = getMapper().findByListEntity(entity);
			System.out.println("总共有:"+page.getTotal()+"条数据,实际返回:"+list.size()+"两条数据!");
		} catch (Exception e) {
			logger.error("查询"+getClassName(entity)+"失败!原因是:",e);
		}
		return list;
	}
	
	@Override
	public List<T> findAll() {
		List<T> list = null;
		try {
			list =  getMapper().findAll();
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
	
	private String getClassName(T t){
		String str="";
		if(t instanceof User){
			str="User";
		}else if(t instanceof Student){
			str="Studnet";
		}
		return str;
	}
}
