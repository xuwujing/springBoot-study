package com.pancm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 
* @Title: ActuatorApp
* @Description:
* @Version:1.0.0  
* @author pancm
* @date 2020年5月6日
 */
@SpringBootApplication
public class AspectApp
{
    public static void main( String[] args )
    {
		SpringApplication.run(AspectApp.class, args);
		System.out.println("Aspect启动成功！");
    }
}
