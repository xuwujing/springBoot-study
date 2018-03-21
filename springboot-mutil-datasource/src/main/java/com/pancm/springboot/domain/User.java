package com.pancm.springboot.domain;

/**
 * 用户实体类
 *
 * Created by bysocket on 07/02/2017.
 */
public class User {

    /**
     * 城市编号
     */
    private int id;

    /**
     * 城市名称
     */
    private String userName;

    /**
     * 描述
     */
    private int age;

    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

	/**  
	 * 获取城市编号  
	 * @return  id  
	 */
	public int getId() {
		return id;
	}

	/**  
	 * 设置城市编号  
	 * @param int id  
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**  
	 * 获取城市名称  
	 * @return  userName  
	 */
	public String getUserName() {
		return userName;
	}

	/**  
	 * 设置城市名称  
	 * @param String userName  
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**  
	 * 获取描述  
	 * @return  age  
	 */
	public int getAge() {
		return age;
	}

	/**  
	 * 设置描述  
	 * @param int age  
	 */
	public void setAge(int age) {
		this.age = age;
	}
    
    
}
