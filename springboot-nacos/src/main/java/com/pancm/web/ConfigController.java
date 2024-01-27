package com.pancm.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @Author pancm
 * @Description 官方示例文档
 * @Date  2024/1/26
 * @Param
 * @return
 **/
@RestController
@RequestMapping("config")
@RefreshScope
public class ConfigController {



    @Value("${pcm.name:pcm-1001}")
    private String name;

    @Value("${pcm.age:18}")
    private String age;


    @RequestMapping(value = "/getAge", method = GET)
    public String getAge() {
        return age;
    }

    @RequestMapping(value = "/getName", method = GET)
    public String getName() {
        return name;
    }


}