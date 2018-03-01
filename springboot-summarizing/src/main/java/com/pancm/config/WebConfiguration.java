package com.pancm.config;

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
//    @Bean
//    public RemoteIpFilter remoteIpFilter() {
//        return new RemoteIpFilter();
//    }
    
    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        //过滤掉 /api 和hello 的所有请求
        registration.addUrlPatterns("/api/*","/hello");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }
    
    
    @Bean
    public String test(){
    	System.out.println("Bean加载测试...");
		return null;
    }
    
    /**
     * 过滤器
     */
    public class MyFilter implements Filter {
		@Override
		public void destroy() {
			System.out.println("开始销毁...");
		}
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
	        if (0<hour && hour < 10) {
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
		public void init(FilterConfig arg0) throws ServletException {
			System.out.println("初始化..."+arg0);
		}
    }
    
}