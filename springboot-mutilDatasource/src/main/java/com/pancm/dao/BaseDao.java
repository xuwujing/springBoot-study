package com.pancm.dao;

import java.util.List;

/**
 * 
* @Title: BaseDao
* @Description:
* 基础数据层 
* @Version:1.0.0  
* @author pancm
* @date 2018年4月12日
 */
public interface BaseDao<T> {
	/**
	 * 插入数据
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 * @throws
	 */
	 int insert(T entity) throws Exception;
	
	/**
	 * 更新数据
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 * @throws
	 */
	 int update(T entity) throws Exception;
	
	/**
	 * 根据ID删除数据
	 * @param id
	 * @throws Exception
	 * @throws
	 */
	 int deleteByPrimaryKey(int id) throws Exception;
	
	/**
	 * 删除数据
	 * @param entity
	 * @throws Exception
	 * @throws
	 */
	 int delete(T entity) throws Exception;
	
	
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
