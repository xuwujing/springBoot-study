package com.pancm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;


/**
 * 
* @Title: App
* @Description:
* 主程序入口 
* @Version:1.0.0  
* @author pancm
* @date 2018年1月9日
 */
//去掉自动注册了
@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
public class App 
{
    public static void main( String[] args )
    {
		SpringApplication.run(App.class, args);
		System.out.println("程序正在运行...");
    }
}
