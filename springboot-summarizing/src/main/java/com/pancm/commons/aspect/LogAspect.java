package com.pancm.commons.aspect;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;


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
    // 使用ThreadLocal存储请求唯一ID
    private static final ThreadLocal<String> requestId = ThreadLocal.withInitial(() -> null);

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
        //生成唯一ID并存储到ThreadLocal
        String uniqueId = UUID.randomUUID().toString().replaceAll("-", "");
        requestId.set(uniqueId);
        // 将唯一ID放入MDC，以便在日志中全局可见
        MDC.put("requestId", uniqueId);
        Class<?> className = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        String path = className.getName().concat("#").concat(methodName);
        log.info("{}|正常请求,请求IP:{},请求人:{},请求路径:{},\n请求内容:{}", uniqueId, getClientIp(), "", path, joinPoint.getArgs());

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
        // 获取存储的唯一ID
        String uniqueId = requestId.get();
        Class<?> className = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        String path = className.getName().concat("#").concat(methodName);
        log.info("{}|正常返回,返回路径:{},\n请求内容:{},\n返回内容:{}", uniqueId,path, joinPoint.getArgs(), JSONObject.toJSONString(returnValue));
    }


    /**
     * @param joinPoint
     * @param e
     * @Title: doAfterThrowing
     * @Description: 异常通知处理方法
     */
    @AfterThrowing(pointcut = "ponitcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        String uniqueId = requestId.get();
        Class<?> className = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        String path = className.getName().concat("#").concat(methodName);
        log.error("{}|异常返回,请求IP:{},返回路径:{},\n返回内容:{},\n异常信息:{},",uniqueId, getClientIp(), path, joinPoint.getArgs(), e.getMessage());
    }




    /**
     * 清除ThreadLocal中的请求ID
     */
    @After("ponitcut()")
    public void cleanUp() {
        requestId.remove();
        MDC.remove("requestId");
    }

    public String getClientIp() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRemoteAddr();
    }

}
