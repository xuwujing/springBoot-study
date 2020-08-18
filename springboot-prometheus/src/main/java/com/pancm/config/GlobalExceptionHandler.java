package com.pancm.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author pancm
 * @Description 全局异常捕获
 * @Date  2020/8/18
 * @Param
 * @return
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private PrometheusCustomMonitor monitor;

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String handle(Exception e) {
        monitor.getRequestErrorCount().increment();
        return "error, count:"+monitor.getRequestErrorCount().count()+" message: " + e.getMessage();
    }
}
