package com.pancm.web;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pancm
 * @Title: springboot-study
 * @Description: 控制层的多线程安全测试
 * @Scope注解：
 * 在@Controller/@Service等容器中，默认情况下，scope值是单例-singleton的，也是线程不安全的。
 * 尽量不要在@Controller/@Service等容器中定义静态变量，不论是单例(singleton)还是多实例(prototype)他都是线程不安全的。
 * 默认注入的Bean对象，在不设置scope的时候他也是线程不安全的。
 * 一定要定义变量的话，用ThreadLocal来封装，这个是线程安全的
 * @Version:1.0.0
 * @Since:jdk1.8
 * @date 2020/10/24
 */
@RestController
@RequestMapping("/safe")
@Scope(value = "prototype") // 加上@Scope注解，他有2个取值：单例-singleton 多实例-prototype
public class SafeController {

    private long  count =0;

    @GetMapping("/test1")
    public String test1(){
        count++;
        System.out.println("count:"+count);
        return null;
    }

}
