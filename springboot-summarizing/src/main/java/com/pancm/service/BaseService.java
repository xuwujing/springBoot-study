package com.pancm.service;

import java.util.List;

/**
 * 
* @Title: BaseService
* @Description:
* 数据层公共实现接口 
* @Version:1.0.0  
* @author pancm
* @date 2018年4月12日
 */
public interface BaseService<T> {
	/**
	 * 插入数据
	 *
	 * @param entity
	 * @return
	 * @throws
	 */
	 boolean insert(T entity) ;
	
	/**
	 * 更新数据
	 *
	 * @param entity
	 * @return
	 * @throws
	 */
	 boolean update(T entity) ;
	
	/**
	 * 根据ID删除数据
	 * @param id
	 * @throws Exception
	 * @throws
	 */
	 boolean deleteByPrimaryKey(int id) ;
	
	/**
	 * 删除数据
	 * @param entity
	 * @throws Exception
	 * @throws
	 */
	 boolean delete(T entity) ;
	
	
	/**
	 * 根据id查询单个记录
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws
	 */
	 T findByPrimaryKey(int id);
	
	/**
	 * 根据对象查询单个记录
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 * @throws
	 */
	 T findByEntity(T entity);
	
	 
	 
	/**
	 * 根据对象查询多条记录
	 * @param entity
	 * @return
	 */
	 List<T> findByListEntity(T entity);
	
	/**
	 * 查询所有记录
	 * @return
	 */
	 List<T> findAll();
	 
	/**
	 * 根据对象查询信息
	 *
	 * @param obj
	 * @return
	 * @throws Exception
	 * @throws
	 */
	 Object findByObject(Object obj);
}
