package com.test.properties;

import com.pancm.util.MyProperties;

/**
 * 
* Title: getMyPropertiesTest
* Description:
* 获取自己自定义的配置文件 
* Version:1.0.0  
* @author pancm
* @date 2018年1月11日
 */
public class getMyPropertiesTest {

	public static void main(String[] args) {
		MyProperties pr =new MyProperties();
		String title=pr.getTitle();
		String des=pr.getDescription();
		
		System.out.println("title:"+title);
		System.out.println("des:"+des);
	}

}
