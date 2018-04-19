package com.pancm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.pancm.util.MyTools;


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
    	
    	 /**
         * 在请求处理之前进行调用（Controller方法调用之前）调用,
         *  返回true 则放行， false 则将直接跳出方法
         */
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
            String token=request.getParameter("token");
            if (MyTools.isEmpty(token)) {
            	 System.out.println("通过!");
                 return true;
            } else {
                System.out.println("不通过!");
                return false;
            }
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
