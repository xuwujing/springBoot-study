package com.pancm.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
* @Title: BaseCtrExc
* @Description:
* 基础异常类 
* @Version:1.0.0  
* @author pancm
* @date 2018年6月19日
 */
@ControllerAdvice
@ResponseBody
public class BaseCtrExc {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        e.printStackTrace();
        return "出错啦!";
    }

}
