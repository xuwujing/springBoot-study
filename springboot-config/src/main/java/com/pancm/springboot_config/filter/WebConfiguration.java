package com.pancm.springboot_config.filter;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 
* Title: WebConfiguration
* Description:
* 自定义过滤器 
* Version:1.0.0  
* @author pancm
* @date 2018年1月4日
 */
@Configuration
public class WebConfiguration {
    
    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        //过滤掉 /getUser 和/hello 的请求
        registration.addUrlPatterns("/getUser","/hello");
        //过滤掉所有请求
//      registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }
    
    
     class MyFilter implements Filter {
		@Override
		public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
				throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest) srequest;
			//执行过滤操作...
			System.out.println("请求的url :"+request.getRequestURI());
			// 获取系统时间
	        Calendar ca = Calendar.getInstance();
	        int hour = ca.get(Calendar.HOUR_OF_DAY);
	        // 设置限制运行时间 
	        if (0<hour && hour < 15) {
	              HttpServletResponse response = (HttpServletResponse) sresponse;
	              response.setCharacterEncoding("UTF-8");
	              response.setContentType("application/json; charset=utf-8");
	              // 消息
	              Map<String, Object> messageMap = new HashMap<>();
	              messageMap.put("status", "1");
	              messageMap.put("message", "此接口可以请求时间为:18-24点");
	              ObjectMapper objectMapper=new ObjectMapper();
	              String writeValueAsString = objectMapper.writeValueAsString(messageMap);
	              response.getWriter().write(writeValueAsString);
	            return;
	        }
			
			filterChain.doFilter(srequest, sresponse);
		}

		@Override
		public void init(FilterConfig filterConfig) throws ServletException {
			System.out.println("参数初始化:"+filterConfig);
		}
		
		@Override
		public void destroy() {
			System.out.println("开始销毁...");
		}
    }
    
}