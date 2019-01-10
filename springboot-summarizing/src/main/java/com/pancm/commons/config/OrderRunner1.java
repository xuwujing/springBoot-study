package com.pancm.commons.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
* @Title: OrderRunner1
* @Description:  CommandLineRunner 测试1
* @Version:1.0.0  
* @author pancm
* @date 2019年1月10日
*/
@Component
@Order(1)
public class OrderRunner1 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.getClass().getName()+"初始化成功!");
    }
}
