package com.pancm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	
	
	  @Insert("insert into t_user(id,name,age) values (#{id},#{name},#{age})")
	  void insert(User user); 
	 
	
	 @Update("update t_user set name=#{name}, age=#{age} where id=#{id}")
	  void update(User user);

	 
	 @Delete("delete from t_user where id=#{id}")
	 void delete(long id);

	 @Select("SELECT * FROM t_user where id=#{id}")
	 User findById(long id);
  

}
