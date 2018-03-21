package com.pancm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;

import com.pancm.bean.User;

/**
 * 
* Title: UserDao
* Description:
* 用户数据接口 
* Version:1.0.0  
* @author pancm
* @date 2018年1月9日
 */
@Mapper
public interface UserDao {
	
	/**
	 * 用户数据新增
	 */
	 @Insert("insert into t_user(id,name,age) values (#{id},#{name},#{age})")
	  void addUser(User user); 
	 
	 /**
	  * 用户数据修改
	  */
	 @Update("update t_user set name=#{name},age=#{age} where id=#{id}")
	  void updateUser(User user);

	 /**
	  * 用户数据删除
	 */
	 @Delete("delete from t_user where id=#{id}")
	 void deleteUser(int id);
	
	 /**
     * 根据用户名称查询用户信息
     *
     */
    @Select("SELECT id,name,age FROM t_user where name=#{userName}")
    User findByName(@Param("userName") String userName);
   
    /**
     * 查询所有用户信息
     */
    List<User> findAll();
    
}
