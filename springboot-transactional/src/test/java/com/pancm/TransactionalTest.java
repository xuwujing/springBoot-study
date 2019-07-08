package com.pancm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pancm.service.UserService;

/**
 * 
* @Title: TransactionalTest
* @Description: 事物测试
* @Version:1.0.0  
* @Since: jdk1.8
* @author pancm
* @date 2019年7月8日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TransactionalApp.class)
public class TransactionalTest {
	
	@Autowired
	private UserService userService;
	
	
	@Test
	public void test1() {
		try {
			userService.test1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void test2() {
		userService.test2();
		
	}
	
	@Test
	public void test3() {
		userService.test3();
	}
	

}
