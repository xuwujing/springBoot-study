package com.pancm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 
* @Title: ActuatorApp
* @Description: 
启动程序之后，在浏览器输入: http://localhost:8284/actuator 加上
 1. /autoconfig   可以得到配置生效信息
 2. /configprops  可以得到属性的内容和默认值
 3. /beans   	     可 以得到bean的别名、类型、是否单例、类的地址、依赖等信息
 4. /dump         可 以得到线程名、线程ID、线程的状态、是否等待锁资源等信息
 5. /env          可以得到环境变量、JVM 属性、命令行参数、项目使用的jar包等信息
 	5.1 /sun.boot.library.path   可以得到JDK安装路径
 6. /health       可以得到磁盘检测和数据库检测等信息
 7. /mappings     可以得到全部的URI路径，以及它们和控制器的映射关系
 8. /metrics      可以得到JVM内容使用、GC情况、类加载信息
    8.1 /gc.*     可以得到GC相关信息
    8.2 /mem.*	     可以得到内存信息
    ... 
 9. /info         可以得到自定义的配置信息
 10. /shutdown    可以进行关闭程序  post请求
 11. /trace       可以得到所Web请求的详细信息
 12./prometheus  可以通过Prometheus查看信息
   
 
* @Version:1.0.0  
* @author pancm
* @date 2019年1月17日
 */
@SpringBootApplication
public class ActuatorApp 
{
    public static void main( String[] args )
    {
		SpringApplication.run(ActuatorApp.class, args);
		System.out.println("Actuator启动成功！");
    }
}
