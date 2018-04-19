package com.pancm.pojo;


/**
 * 
* @Title: Student
* @Description:
* 学生信息表 
* @Version:1.0.0  
* @author pancm
* @date 2018年4月9日
 */
public class Student {
	
	/** 学生编号*/
	private Long id;
	/** 学生姓名*/
	private String name;
	/** 学生年龄*/
	private Integer age;
	/**  
	 * 获取学生编号  
	 * @return  id  
	 */
	public Long getId() {
		return id;
	}
	/**  
	 * 设置学生编号  
	 * @param Long id  
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**  
	 * 获取学生姓名  
	 * @return  name  
	 */
	public String getName() {
		return name;
	}
	/**  
	 * 设置学生姓名  
	 * @param String name  
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**  
	 * 获取学生年龄  
	 * @return  age  
	 */
	public Integer getAge() {
		return age;
	}
	/**  
	 * 设置学生年龄  
	 * @param Integer age  
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
