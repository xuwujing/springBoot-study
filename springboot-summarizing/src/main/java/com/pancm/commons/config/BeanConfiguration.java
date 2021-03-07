package com.pancm.commons.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Properties;

/**
 * @author pancm
 * @Title: springboot-study
 * @Description: 获取配置文件
 * @Version:1.0.0
 * @Since:jdk1.8
 * @date 2021/3/7
 */
@Configuration
public class BeanConfiguration {
    @Bean
    public YamlConfigurerUtil ymlConfigurerUtil() {
        //1:加载配置文件
        Resource app = new ClassPathResource("application.yml");
        Resource appDev = new ClassPathResource("application-dev.yml");
        Resource appPre = new ClassPathResource("application-pre.yml");
        Resource appProd = new ClassPathResource("application-prod.yml");
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        // 2:将加载的配置文件交给 YamlPropertiesFactoryBean
        yamlPropertiesFactoryBean.setResources(app, appDev, appProd, appPre);
        // 3：将yml转换成 key：val
        Properties properties = yamlPropertiesFactoryBean.getObject();
        // 4: 将Properties 通过构造方法交给我们写的工具类
        YamlConfigurerUtil ymlConfigurerUtil = new YamlConfigurerUtil(properties);
        return ymlConfigurerUtil;
    }
}
