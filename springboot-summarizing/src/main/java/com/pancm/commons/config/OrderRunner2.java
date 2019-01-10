package com.pancm.commons.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
* @Title: OrderRunner2
* @Description:  CommandLineRunner 测试2
* @Version:1.0.0  
* @author pancm
* @date 2019年1月10日
*/
@Component
@Order(1)
public class OrderRunner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.getClass().getName()+"初始化成功!");
    }
}
