package com.pancm.pojo;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * 
* Title: User
* Description:用户pojo类
* Version:1.0.0  
* @author pancm
* @date 2017年9月26日
 */
public class User implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 编号 */
	 private Integer id;
	 /** 姓名 */
	 private String name;
	 
	 /** 年龄 */
	 private Integer age;

	 public User() {
	 }
	 
	 public User(Integer id, String name, Integer age) {
			super();
			this.id = id;
			this.name = name;
			this.age = age;
	}
		 
	 
	/**  
	 * 获取id  
	 * @return  id  
	 */
	public Integer getId() {
		return id;
	}

	/**  
	 * 设置id  
	 * @param Integer id  
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**  
	 * 获取name  
	 * @return  name  
	 */
	public String getName() {
		return name;
	}

	/**  
	 * 设置name  
	 * @param String name  
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**  
	 * 获取age  
	 * @return  age  
	 */
	public Integer getAge() {
		return age;
	}

	/**  
	 * 设置age  
	 * @param Integer age  
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/** 
	 * 
	 */
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	
	
	
	

}
