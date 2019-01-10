package com.pancm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 
 * @Title: Application
 * @Description: springBoot 主程序
 * @Version:1.0.0
 * @author pancm
 * @date 2018年1月5日
 */

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	/*
	 * SpringApplication 则是用于从main方法启动Spring应用的类。默认，它会执行以下步骤：
	 * 1.创建一个合适的ApplicationContext实例 （取决于classpath）。
	 * 2.注册一个CommandLinePropertySource，以便将命令行参数作为Spring properties。 3.刷新application
	 * context，加载所有单例beans。 4.激活所有CommandLineRunner beans。
	 */
	public static void main(String[] args) {
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(Application.class, args);
		logger.info("程序启动成功!");
	}

	@Override
	public void run(String... strings) throws Exception {
		logger.debug("启动一个方法测试...");
	}
	
	/**
	 * 将JSON序列化换成FastJson
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastConverter.setFastJsonConfig(fastJsonConfig);

		converters.add(fastConverter);
	}
}
