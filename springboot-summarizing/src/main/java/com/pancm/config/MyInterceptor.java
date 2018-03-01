package com.pancm.config;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
* Title: MyInterceptor
* Description:
* 自定义拦截器 
* Version:1.0.0  
* @author pancm
* @date 2018年2月27日
 */
public class MyInterceptor implements HandlerInterceptor {
	
	@Autowired  
    private IpConfig ipconfig; 
	
	 /**
     * 在请求处理之前进行调用（Controller方法调用之前）调用,
     *  返回true 则放行， false 则将直接跳出方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        String ip = getIpAddr(request);
        // 获取可以访问系统的白名单
        String ipStr = ipconfig.getIpWhiteList();
        String[] ipArr = ipStr.split("\\|");
        List<String> ipList = Arrays.asList(ipArr);

        if (ipList.contains(ip)) {
        	 System.out.println("该IP: " + ip+"通过!");
             return true;
        } else {
            System.out.println("该IP: " + ip+"不通过!");
            response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
            String data = "您好，ip为" + ip + ",暂时没有访问权限，请联系管理员开通访问权限。";
            OutputStream stream = response.getOutputStream();
            stream.write(data.getBytes("UTF-8"));
            return false;
        }
    }

    /**
     * 获取访问的ip地址
     * 
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
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
