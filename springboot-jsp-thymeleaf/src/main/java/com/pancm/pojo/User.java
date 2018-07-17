package com.pancm.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
* Title: User
* Description:用户pojo类
* Version:1.0.0  
* @author pancm
* @date 2017年9月26日
 */
@Entity
@Table(name = "t_user")
public class User {
	
	 /** 编号 */
	 @Id
	 @GeneratedValue
	 private Long id;
	 /** 姓名 */
	 @Column(nullable = false, unique = true)
	 private String name;
	 /** 密码*/
	 @Column(nullable = false)
	 private String password;
	 /** 年龄 */
	 @Column(nullable = false)
	 private Integer age;
	 
	 public User(){
	 }

	/**  
	 * 获取编号  
	 * @return id 
	 */
	public Long getId() {
		return id;
	}

	/**  
	 * 设置编号  
	 * @param id 
	 */
	public void setId(Long id) {
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
	 * 获取password  
	 * @return  password  
	 */
	public String getPassword() {
		return password;
	}

	/**  
	 * 设置password  
	 * @param String password  
	 */
	public void setPassword(String password) {
		this.password = password;
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
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", age=" + age + "]";
	}
	
	
	

}
