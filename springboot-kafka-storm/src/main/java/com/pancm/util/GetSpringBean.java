package com.pancm.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * spring动态获取bean实现
 * @author xuwujing 
 */
public class GetSpringBean implements ApplicationContextAware{

	private static ApplicationContext context;

	public static Object getBean(String name) {
		return context.getBean(name);
	}

	public static <T> T getBean(Class<T> c) {

		return context.getBean(c);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}

}
