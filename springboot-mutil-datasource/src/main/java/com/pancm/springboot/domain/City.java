package com.pancm.springboot.domain;


/**
 * 城市实体类
 *
 * Created by bysocket on 07/02/2017.
 */
/**
 * 
* Title: City
* Description: 
* Version:1.0.0  
* @author pancm
* @date 2018年3月20日
 */
public class City {

    /**
     * 城市编号
     */
    private int id;

    /**
     * 城市名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

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
	 * @return  name  
	 */
	public String getName() {
		return name;
	}

	/**  
	 * 设置城市名称  
	 * @param String name  
	 */
	public void setName(String name) {
		this.name = name;
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

}
