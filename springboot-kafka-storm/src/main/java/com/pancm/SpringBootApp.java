package com.pancm;


import com.pancm.util.GetSpringBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author pancm
 * @Title: springBoot-study
 * @Description:
 * @Version:1.0.0
 * @Since:jdk1.8
 * @date 2019/8/22
 */
@SpringBootApplication
public class SpringBootApp {

    public static void run(String args) {
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        GetSpringBean springBean=new GetSpringBean();
        springBean.setApplicationContext(context);
    }
}
