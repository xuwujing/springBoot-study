package com.pancm.pojo;
/**
 * 
* @Title: User
* @Description:用户pojo类
* @Version:1.0.0  
* @author pancm
* @date 2017年9月26日
 */
public class User {
	 /** 编号 */
	 private Long id;
	 /** 姓名 */
	 private String name;
	 
	 /** 年龄 */
	 private Integer age;
	 
	 public User(){
	 }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	/** 
	 * 
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
	

}
