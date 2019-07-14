package com.pancm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * 
* @Title: App
* @Description:
* 主程序入口 
* @Version:1.0.0  
* @author pancm
* @date 2018年1月9日
 */
@EnableTransactionManagement
@SpringBootApplication
public class TransactionalApp
{
		
    public static void main( String[] args )
    {
		SpringApplication.run(TransactionalApp.class, args);
		System.out.println("Transactional 程序正在运行...");
	
    }
}
