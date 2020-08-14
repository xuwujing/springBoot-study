package com.pancm.commons.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * @Author pancm
 * @Description 日志记录切面
 * @Date 2020/8/13
 * @Param
 * @return
 **/
@Aspect
@Component
@Slf4j
@Order(-1)
public class LogAspect {


    /**
     * @Title: logExecution
     * @Description: 切入点表达式
     */
    @Pointcut("@annotation(com.pancm.commons.annotation.LogAnnotation)")
    public void ponitcut() {
    }

    /**
     * @param joinPoint
     * @Title: before
     * @Description: 前置通知处理方法
     */
    @Before("ponitcut()")
    public void before(JoinPoint joinPoint) {
        log.info("{}|请求ip:{},内容:{}", getHeadId(), getIp(), joinPoint.getArgs());
    }

    /**
     * @param joinPoint
     * @param
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @Title: doAfterThrowing
     * @Description: 后置通知处理方法
     */
    @AfterReturning(pointcut = "ponitcut()", returning = "returnValue")
    public void doafterReturning(JoinPoint joinPoint, Object returnValue) {
        log.info("{}|返回ip:{},内容:{}", getHeadId(), getIp(), returnValue);
    }


    /**
     * @param joinPoint
     * @param e
     * @Title: doAfterThrowing
     * @Description: 异常通知处理方法
     */
    @AfterThrowing(pointcut = "ponitcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Class<?> className = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        log.warn("{}|异常返回ip:{},class:{},method:{},异常内容:{}", getHeadId(), getIp(), className.getName(), methodName, e.getMessage());
    }


    public String getIp() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRemoteAddr();
    }

    private String getHeadId() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("reqId");
    }

}
