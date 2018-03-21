package com.pancm.result;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorInfoHandler {
 
@ExceptionHandler(value = GlobalErrorInfoException.class)
public ResultBody errorHandlerOverJson(HttpServletRequest request,
GlobalErrorInfoException exception) {
    ErrorInfoInterface errorInfo = exception.getErrorInfo();
    ResultBody result = new ResultBody(errorInfo);
    return result;
}
}
