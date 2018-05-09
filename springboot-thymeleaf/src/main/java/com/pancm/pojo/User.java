package com.pancm.pojo;

/**
 * 
* Title: User
* Description:用户pojo类
* Version:1.0.0  
* @author pancm
* @date 2017年9月26日
 */
public class User {
	
	 /** 编号 */
	 private int id;
	 /** 姓名 */
	 private String name;
	 /** 密码*/
	 private String password;
	 /** 年龄 */
	 private int age;
	 
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
	 * 获取密码  
	 * @return  password  
	 */
	public String getPassword() {
		return password;
	}

	/**  
	 * 设置密码  
	 * @param String password  
	 */
	public void setPassword(String password) {
		this.password = password;
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
	
	

}
