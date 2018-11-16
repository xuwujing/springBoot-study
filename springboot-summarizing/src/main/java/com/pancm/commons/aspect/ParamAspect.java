package com.pancm.commons.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 
* @Title: ParamAspect
* @Description: 利用断言实现请求控制层的时候 调用该方法
* @Version:1.0.0  
* @author pancm
* @date 2018年9月14日
 */
@Aspect
@Component
public class ParamAspect {

	
	@Before("execution(public * com.pancm.web.*.*(..))")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
		Object[] params= joinPoint.getArgs();
		for (int i = 0; i < params.length; i++) {
//			ValidateHelper.asertPass(params[i]);
		}
    }

}
