package com.test.properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
* Title: getMyPropertiesTest
* Description:
* 获取自己自定义的配置文件 
* Version:1.0.0  
* @author pancm
* @date 2018年1月11日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= getMyPropertiesTest.class)
public class getMyPropertiesTest {
	
	@Value("${test.msg}")
	private String msg;
	
    @Test
    public void test() throws Exception {
    	System.out.println("==="+msg);
    }
    
}
