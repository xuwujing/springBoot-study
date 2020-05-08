package com.pancm.aspect;


import com.pancm.pojo.User;
import com.pancm.result.ResultBody;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;


/**
 * @Author pancm
 * @Description
 * @Date  2020/5/6
 * @Param
 * @return
 **/
@Aspect
@Component
public class ParamAspect {


    @Pointcut("execution(public * com.pancm.web.*.*(..))")
    public void doOperation() {

    }


    /**
     * @Title: before
     * @Description: 前置通知处理方法
     *    在处理之前调用，比如参数、权限校验
     * @param joinPoint
     */
    @Before("doOperation()")
    public void before(JoinPoint joinPoint) throws Throwable{
        Object[] objs = joinPoint.getArgs();
        for (Object obj : objs) {
            User user = (User) obj;
            System.out.println("请求的user:"+user);
            user.setName(base64DeStr(user.getName()));
        }
    }




//    @Around("doOperation()")
//    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object[] objs = joinPoint.getArgs();
//        for (Object obj : objs) {
//            User user = (User) obj;
//            System.out.println("请求的user:"+user);
//            user.setAge(17);
//            return joinPoint.proceed(objs);
//        }
//        return joinPoint.proceed(objs);
//    }



    @AfterReturning(returning = "object", pointcut = "doOperation()")
    public void doAfterReturning(Object object) {
        ResultBody resultBody = (ResultBody) object;
        String str =null;
        try {
             str=base64EnStr(resultBody.getResult());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        resultBody.setResult(str);
    }


    public  String base64EnStr(String str) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
    }


    public static String base64DeStr(String encodeStr) throws UnsupportedEncodingException {
        byte[] decodeStr = Base64.getDecoder().decode(encodeStr);
        return new String(decodeStr, "UTF-8");
    }

}