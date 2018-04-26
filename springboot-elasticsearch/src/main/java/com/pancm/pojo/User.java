package com.pancm.pojo;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 
* @Title: User
* @Description:
* 用户实体类 
* indexName:索引库的名称,类似数据库 
* type:类型，类似表
* @Version:1.0.0  
* @author pancm
* @date 2018年4月24日
 */
@Document(indexName = "userindex", type = "user")
public class User implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 编号 */
	 private int id;
	 /** 姓名 */
	 private String name;
	 
	 /** 年龄 */
	 private int age;
	 
	 /** 描述 */  
	 private String description;
	 
	 
	 public User(){
	 }
	 

	/**  
	 * 获取编号  
	 * @return id 
	 */
	public int getId() {
		return id;
	}

	/**  
	 * 设置编号  
	 * @param id 
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**  
	 * 获取姓名  
	 * @return name 
	 */
	public String getName() {
		return name;
	}

	/**  
	 * 设置姓名  
	 * @param name 
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**  
	 * 获取年龄  
	 * @return  age  
	 */
	public int getAge() {
		return age;
	}
	/**  
	 * 设置年龄  
	 * @param int age  
	 */
	public void setAge(int age) {
		this.age = age;
	}


	/**  
	 * 获取描述  
	 * @return  description  
	 */
	public String getDescription() {
		return description;
	}


	/**  
	 * 设置描述  
	 * @param String description  
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/** 
	 * 
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age
				+ ", description=" + description + "]";
	}
	
	

}
