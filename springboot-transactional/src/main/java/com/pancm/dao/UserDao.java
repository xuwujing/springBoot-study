package com.pancm.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pancm.pojo.User;

/**
 * 
* @Title: UserDao
* @Description:
* 用户数据接口 
* @Version:1.0.0
* @author pancm
* @date 2018年1月9日
 */
@Mapper
public interface UserDao{
    /**
     * 单条新增插入数据
     * @param entity
     * @return
     * @throws Exception
     * @throws
     */
    void insert(User entity) throws Exception;


    User findById(long id);
    
    User findByListEntity(User entity);
    
    /**
     * 更新数据
     *
     * @param entity
     * @return
     * @throws Exception
     * @throws
     */
    void update(User entity) throws Exception;



    /**
     * 删除数据
     * @param entity
     * @throws Exception
     * @throws
     */
    void delete(User entity) throws Exception;


}
