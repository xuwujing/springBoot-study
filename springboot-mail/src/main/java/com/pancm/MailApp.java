package com.pancm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 
* @Title: ActuatorApp
* @Description: 邮件发送

  注: 如果出现了535 错误，需要开启POP3/SMTP服务，并且使用授权码替换密码进行发送!!!
* @Version:1.0.0  
* @author pancm
* @date 2019年1月17日
 */
@SpringBootApplication
public class MailApp 
{
    public static void main( String[] args )
    {
		SpringApplication.run(MailApp.class, args);
		System.out.println("MailApp启动成功！");
    }
}
