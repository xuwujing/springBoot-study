package com.pancm.aspect;


import com.pancm.pojo.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


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

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.pancm.web.*.*(..))")
    public void doOperation() {

        log.info("参数检验AOP");
    }

    @Around("doOperation()")
    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] objs = joinPoint.getArgs();
        for (Object obj : objs) {
            User user = (User) obj;
            System.out.println("请求的user:"+user);
            user.setAge(17);
            return joinPoint.proceed(objs);
        }
        return joinPoint.proceed(objs);
    }



    @AfterReturning(returning = "object", pointcut = "doOperation()")
    public void doAfterReturning(Object object) {
        System.out.println("=="+object);

//        RequestVo responseVo = (RequestVo) object;
//        byte[] a = Base64Utils.encode(String.valueOf(responseVo.getScont()).getBytes());
//        responseVo.setMsg(new String(a ));
//        responseVo.setScont("123");

//        log.info("请求返回值【{}】", object.toString());
    }

}