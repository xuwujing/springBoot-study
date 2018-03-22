package com.pancm.interceptor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pancm.config.IpConfig;


/**
 * 
* Title: MyWebInterceptor
* Description:
* 自定义拦截器 
* Version:1.0.0  
* @author pancm
* @date 2018年3月20日
 */
@Configuration
public class MyWebInterceptor extends WebMvcConfigurerAdapter {

    @Bean   
    public HandlerInterceptor getMyInterceptor(){
        return new MyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则, 这里假设拦截 /url 后面的全部链接
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
    
    
     class MyInterceptor implements HandlerInterceptor {
    	
    	@Autowired  
        private IpConfig ipconfig; 
    	
    	 /**
         * 在请求处理之前进行调用（Controller方法调用之前）调用,
         *  返回true 则放行， false 则将直接跳出方法
         */
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
            String ip = getIpAddr(request);
            String token=request.getParameter("token");
            if (!ip.isEmpty()) {
            	 System.out.println("该IP: " + ip+"通过!");
                 return true;
            } else {
                System.out.println("该IP: " + ip+"不通过!");
                return false;
            }
        }

        /**
         * 获取访问的ip地址
         * 
         * @param request
         * @return
         */
        public  String getIpAddr(HttpServletRequest request) {
            String ip = request.getHeader("X-Forwarded-For");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            return ip;
        }

        //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
        @Override
        public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
            System.out.println("postHandle被调用");
        }

        //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
        @Override
        public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
            System.out.println("afterCompletion被调用");
        }
    }
    
    
    
    
    
}
