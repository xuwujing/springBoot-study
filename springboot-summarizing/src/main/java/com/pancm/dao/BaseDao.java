package com.pancm.dao;

public interface BaseDao<T> {
	/**
	 * 插入数据
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public int insert(T entity) throws Exception;
	
	/**
	 * 更新数据
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public int updateByPrimaryKey(T entity) throws Exception;
	
	/**
	 * 删除数据
	 *
	 * @param id
	 * @throws Exception
	 * @throws
	 */
	public int deleteByPrimaryKey(int id) throws Exception;
	
	/**
	 * 删除数据
	 *
	 * @param entity
	 * @throws Exception
	 * @throws
	 */
	public int delete(T entity) throws Exception;
	
	/**
	 * 根据id查询单个记录
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public T selectByPrimaryKey(int id);
	
	/**
	 * 根据对象查询单个记录
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public T getOne(T entity);
	
	/**
	 * 根据对象查询信息
	 *
	 * @param obj
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public Object getObject(Object obj);
	
	
	
	
	
}
