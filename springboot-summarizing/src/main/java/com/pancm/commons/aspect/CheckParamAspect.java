package com.pancm.commons.aspect;


import java.lang.reflect.Method;
import java.util.Collection;
import java.util.function.BiFunction;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.pancm.commons.annotation.Check;
import com.pancm.commons.enums.CommonEnum;
import com.pancm.commons.result.ResultBody;
import com.pancm.commons.util.ReflectionUtil;

/**
 * 
* @Title: CheckParamAspect
* @Description: 
* 参数校验 切面
* @Version:1.0.0  
* @author pancm
* @date 2018年5月10日
 */
@Aspect
@SuppressWarnings("rawtypes")
public class CheckParamAspect {

    private static final Logger LOG = LoggerFactory.getLogger(CheckParamAspect.class);
    
    /**
	 * @Title: logExecution
	 * @Description: 定义一个切入点表达式
	 */
	@Pointcut("@annotation(com.pancm.commons.annotation.Check)")
	public void ponitcut() {}
    
	/**
	 * @Title: before
	 * @Description: 前置通知处理方法
	 *    在处理之前调用，比如参数、权限校验
	 * @param joinPoint
	 */
	@Before("ponitcut()")
	public void before(JoinPoint joinPoint) throws Throwable{
		LOG.info("开始调用。。。");
	}
	
	/**
	 *     后置通知处理方法
	 *     在处理之后调用，比如记录日志
	 * @param joinPoint
	 * @param returnValue
	 */
	@AfterReturning(pointcut="ponitcut()", returning="returnValue")
	public void doafterReturning(JoinPoint joinPoint, Object returnValue) {
		LOG.info("结束调用。。。");
	}
	
	
	/**
	 * @Description: 异常通知处理方法
	 * 在出现异常的时候进行调用
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "ponitcut()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		LOG.error("出现异常啦！",e);
	}
	
	/**
	 *    环绕通知处理方法
	 * @param point
	 * @return
	 * @throws Throwable
	 */
    @Around(value = "@com.pancm.commons.annotation") // 这里要换成自定义注解的路径
    public Object check(ProceedingJoinPoint point) throws Throwable {
        Object obj;
        // 参数校验
        String msg = doCheck(point);
        if (!StringUtils.isEmpty(msg)) {
         //   throw new BizException(msg);
        	return   ResultBody.error(CommonEnum.BODY_NOT_MATCH);
        }
        LOG.info("参数校验通过!");
        // 通过校验，继续执行原有方法
        obj = point.proceed();
        return obj;
    }

    /**
     * 参数校验
     *
     * @param point ProceedingJoinPoint
     * @return 错误信息
     */
    private String doCheck(ProceedingJoinPoint point) {
        // 获取方法参数值
        Object[] arguments = point.getArgs();
        // 获取方法
        Method method = getMethod(point);
        // 默认的错误信息
        String methodInfo = StringUtils.isEmpty(method.getName()) ? "" : " while calling " + method.getName();
        String msg = "";
        if (isCheck(method, arguments)) {
            Check annotation = method.getAnnotation(Check.class);
            String[] fields = annotation.value();
            // 只支持对第一个参数进行校验
            Object vo = arguments[0];
            if (vo == null) {
                msg = "param can not be null";
            } else {
                for (String field : fields) {
                    // 解析字段
                    FieldInfo info = resolveField(field, methodInfo);
                    // 获取字段的值
                    Object value = ReflectionUtil.invokeGetter(vo, info.field);
                    // 执行校验规则
                    Boolean isValid = info.optEnum.fun.apply(value, info.operatorNum);
                    msg = isValid ? msg : info.innerMsg;
                }
            }
        }
        return msg;
    }

    /**
     * 解析字段
     *
     * @param fieldStr   字段字符串
     * @param methodInfo 方法信息
     * @return 字段信息实体类
     */
    private FieldInfo resolveField(String fieldStr, String methodInfo) {
        FieldInfo fieldInfo = new FieldInfo();
        String innerMsg = "";
        // 解析提示信息
        if (fieldStr.contains(SEPARATOR)) {
            innerMsg = fieldStr.split(SEPARATOR)[1];
            fieldStr = fieldStr.split(SEPARATOR)[0];
        }
        // 解析操作符
        if (fieldStr.contains(Operator.GREATER_THAN_EQUAL.value)) {
            fieldInfo.optEnum = Operator.GREATER_THAN_EQUAL;
        } else if (fieldStr.contains(Operator.LESS_THAN_EQUAL.value)) {
            fieldInfo.optEnum = Operator.LESS_THAN_EQUAL;
        } else if (fieldStr.contains(Operator.GREATER_THAN.value)) {
            fieldInfo.optEnum = Operator.GREATER_THAN;
        } else if (fieldStr.contains(Operator.LESS_THAN.value)) {
            fieldInfo.optEnum = Operator.LESS_THAN;
        } else if (fieldStr.contains(Operator.NOT_EQUAL.value)) {
            fieldInfo.optEnum = Operator.NOT_EQUAL;
        } else {
            fieldInfo.optEnum = Operator.NOT_NULL;
        }
        // 不等于空，直接赋值字段
        if (fieldInfo.optEnum == Operator.NOT_NULL) {
            fieldInfo.field = fieldStr;
            fieldInfo.operatorNum = "";
        }
        // 其他操作符，需要分离出字段和操作数
        else {
            fieldInfo.field = fieldStr.split(fieldInfo.optEnum.value)[0];
            fieldInfo.operatorNum = fieldStr.split(fieldInfo.optEnum.value)[1];
        }
        fieldInfo.operator = fieldInfo.optEnum.value;
        // 处理错误信息
        String defaultMsg = fieldInfo.field + " must " + fieldInfo.operator + " " + fieldInfo.operatorNum + methodInfo;
        fieldInfo.innerMsg = StringUtils.isEmpty(innerMsg) ? defaultMsg : innerMsg;
        return fieldInfo;
    }

    // -=================== 对不同类型的值进行校验 起 =======================

    /**
     * 是否不为空
     *
     * @param value       字段值
     * @param operatorNum 操作数，这里不需要，只是为了参数统一
     * @return 是否不为空
     */
    private static Boolean isNotNull(Object value, String operatorNum) {
        Boolean isNotNull = Boolean.TRUE;
        Boolean isStringNull = (value instanceof String) && StringUtils.isEmpty((String) value);
		Boolean isCollectionNull = (value instanceof Collection) && CollectionUtils.isEmpty((Collection) value);
        if (value == null) {
            isNotNull = Boolean.FALSE;
        } else if (isStringNull || isCollectionNull) {
            isNotNull = Boolean.FALSE;
        }
        return isNotNull;
    }

    /**
     * 是否大于
     *
     * @param value       字段值
     * @param operatorNum 操作数
     * @return 是否大于
     */

	private static Boolean isGreaterThan(Object value, String operatorNum) {
        Boolean isGreaterThan = Boolean.FALSE;
        if (value == null) {
            return Boolean.FALSE;
        }
        Boolean isStringGreaterThen = (value instanceof String) && ((String) value).length() > Integer.valueOf(operatorNum);
        Boolean isLongGreaterThen = (value instanceof Long) && ((Long) value) > Long.valueOf(operatorNum);
        Boolean isIntegerGreaterThen = (value instanceof Integer) && ((Integer) value) > Integer.valueOf(operatorNum);
        Boolean isShortGreaterThen = (value instanceof Short) && ((Short) value) > Short.valueOf(operatorNum);
        Boolean isFloatGreaterThen = (value instanceof Float) && ((Float) value) > Float.valueOf(operatorNum);
        Boolean isDoubleGreaterThen = (value instanceof Double) && ((Double) value) > Double.valueOf(operatorNum);
        Boolean isCollectionGreaterThen = (value instanceof Collection) && ((Collection) value).size() > Integer.valueOf(operatorNum);
        if (isStringGreaterThen || isLongGreaterThen || isIntegerGreaterThen ||
                isShortGreaterThen || isFloatGreaterThen || isDoubleGreaterThen || isCollectionGreaterThen) {
            isGreaterThan = Boolean.TRUE;
        }
        return isGreaterThan;
    }

    /**
     * 是否大于等于
     *
     * @param value       字段值
     * @param operatorNum 操作数
     * @return 是否大于等于
     */
    private static Boolean isGreaterThanEqual(Object value, String operatorNum) {
        Boolean isGreaterThanEqual = Boolean.FALSE;
        if (value == null) {
            return Boolean.FALSE;
        }
        Boolean isStringGreaterThenEqual = (value instanceof String) && ((String) value).length() >= Integer.valueOf(operatorNum);
        Boolean isLongGreaterThenEqual = (value instanceof Long) && ((Long) value) >= Long.valueOf(operatorNum);
        Boolean isIntegerGreaterThenEqual = (value instanceof Integer) && ((Integer) value) >= Integer.valueOf(operatorNum);
        Boolean isShortGreaterThenEqual = (value instanceof Short) && ((Short) value) >= Short.valueOf(operatorNum);
        Boolean isFloatGreaterThenEqual = (value instanceof Float) && ((Float) value) >= Float.valueOf(operatorNum);
        Boolean isDoubleGreaterThenEqual = (value instanceof Double) && ((Double) value) >= Double.valueOf(operatorNum);
        Boolean isCollectionGreaterThenEqual = (value instanceof Collection) && ((Collection) value).size() >= Integer.valueOf(operatorNum);
        if (isStringGreaterThenEqual || isLongGreaterThenEqual || isIntegerGreaterThenEqual ||
                isShortGreaterThenEqual || isFloatGreaterThenEqual || isDoubleGreaterThenEqual || isCollectionGreaterThenEqual) {
            isGreaterThanEqual = Boolean.TRUE;
        }
        return isGreaterThanEqual;
    }

    /**
     * 是否少于
     *
     * @param value       字段值
     * @param operatorNum 操作数
     * @return 是否少于
     */
	private static Boolean isLessThan(Object value, String operatorNum) {
        Boolean isLessThan = Boolean.FALSE;
        if (value == null) {
            return Boolean.FALSE;
        }
        Boolean isStringLessThen = (value instanceof String) && ((String) value).length() < Integer.valueOf(operatorNum);
        Boolean isLongLessThen = (value instanceof Long) && ((Long) value) < Long.valueOf(operatorNum);
        Boolean isIntegerLessThen = (value instanceof Integer) && ((Integer) value) < Integer.valueOf(operatorNum);
        Boolean isShortLessThen = (value instanceof Short) && ((Short) value) < Short.valueOf(operatorNum);
        Boolean isFloatLessThen = (value instanceof Float) && ((Float) value) < Float.valueOf(operatorNum);
        Boolean isDoubleLessThen = (value instanceof Double) && ((Double) value) < Double.valueOf(operatorNum);
        Boolean isCollectionLessThen = (value instanceof Collection) && ((Collection) value).size() < Integer.valueOf(operatorNum);
        if (isStringLessThen || isLongLessThen || isIntegerLessThen ||
                isShortLessThen || isFloatLessThen || isDoubleLessThen || isCollectionLessThen) {
            isLessThan = Boolean.TRUE;
        }
        return isLessThan;
    }

    /**
     * 是否少于等于
     *
     * @param value       字段值
     * @param operatorNum 操作数
     * @return 是否少于等于
     */
    private static Boolean isLessThanEqual(Object value, String operatorNum) {
        Boolean isLessThanEqual = Boolean.FALSE;
        if (value == null) {
            return Boolean.FALSE;
        }
        Boolean isStringLessThenEqual = (value instanceof String) && ((String) value).length() <= Integer.valueOf(operatorNum);
        Boolean isLongLessThenEqual = (value instanceof Long) && ((Long) value) <= Long.valueOf(operatorNum);
        Boolean isIntegerLessThenEqual = (value instanceof Integer) && ((Integer) value) <= Integer.valueOf(operatorNum);
        Boolean isShortLessThenEqual = (value instanceof Short) && ((Short) value) <= Short.valueOf(operatorNum);
        Boolean isFloatLessThenEqual = (value instanceof Float) && ((Float) value) <= Float.valueOf(operatorNum);
        Boolean isDoubleLessThenEqual = (value instanceof Double) && ((Double) value) <= Double.valueOf(operatorNum);
        Boolean isCollectionLessThenEqual = (value instanceof Collection) && ((Collection) value).size() <= Integer.valueOf(operatorNum);
        if (isStringLessThenEqual || isLongLessThenEqual || isIntegerLessThenEqual ||
                isShortLessThenEqual || isFloatLessThenEqual || isDoubleLessThenEqual || isCollectionLessThenEqual) {
            isLessThanEqual = Boolean.TRUE;
        }
        return isLessThanEqual;
    }

    /**
     * 是否不等于
     *
     * @param value       字段值
     * @param operatorNum 操作数
     * @return 是否不等于
     */
	private static Boolean isNotEqual(Object value, String operatorNum) {
        Boolean isNotEqual = Boolean.FALSE;
        if (value == null) {
            return Boolean.FALSE;
        }
        Boolean isStringNotEqual = (value instanceof String) && !value.equals(operatorNum);
        Boolean isLongNotEqual = (value instanceof Long) && !value.equals(Long.valueOf(operatorNum));
        Boolean isIntegerNotEqual = (value instanceof Integer) && !value.equals(Integer.valueOf(operatorNum));
        Boolean isShortNotEqual = (value instanceof Short) && !value.equals(Short.valueOf(operatorNum));
        Boolean isFloatNotEqual = (value instanceof Float) && !value.equals(Float.valueOf(operatorNum));
        Boolean isDoubleNotEqual = (value instanceof Double) && !value.equals(Double.valueOf(operatorNum));
        Boolean isCollectionNotEqual = (value instanceof Collection) && ((Collection) value).size() != Integer.valueOf(operatorNum);
        if (isStringNotEqual || isLongNotEqual || isIntegerNotEqual ||
                isShortNotEqual || isFloatNotEqual || isDoubleNotEqual || isCollectionNotEqual) {
            isNotEqual = Boolean.TRUE;
        }
        return isNotEqual;
    }

    // -=================== 对不同类型的值进行校验 止 =======================

    /**
     * 判断是否符合参数规则
     *
     * @param method    方法
     * @param arguments 方法参数
     * @return 是否符合
     */
    private Boolean isCheck(Method method, Object[] arguments) {
        Boolean isCheck = Boolean.TRUE;
        // 只允许有一个参数
        if (!method.isAnnotationPresent(Check.class)
                || arguments == null
                || arguments.length != 1) {
            isCheck = Boolean.FALSE;
        }
        return isCheck;
    }

    /**
     * 获取方法
     *
     * @param joinPoint ProceedingJoinPoint
     * @return 方法
     */
    private Method getMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint
                        .getTarget()
                        .getClass()
                        .getDeclaredMethod(joinPoint.getSignature().getName(),
                                method.getParameterTypes());
            } catch (SecurityException | NoSuchMethodException e) {
                LOG.error("获取方法异常:" + e);
            }
        }
        return method;
    }

    /**
     * 字段信息
     */
    class FieldInfo {
        /**
         * 字段
         */
        String field;
        /**
         * 提示信息
         */
        String innerMsg;
        /**
         * 操作符
         */
        String operator;
        /**
         * 操作数
         */
        String operatorNum;
        /**
         * 操作枚举
         */
        Operator optEnum;
    }

    /**
     * 操作枚举，封装操作符和对应的校验规则
     */
    enum Operator {
        /**
         * 大于
         */
        GREATER_THAN(">", CheckParamAspect::isGreaterThan),
        /**
         * 大于等于
         */
        GREATER_THAN_EQUAL(">=", CheckParamAspect::isGreaterThanEqual),
        /**
         * 小于
         */
        LESS_THAN("<", CheckParamAspect::isLessThan),
        /**
         * 小于等于
         */
        LESS_THAN_EQUAL("<=", CheckParamAspect::isLessThanEqual),
        /**
         * 不等于
         */
        NOT_EQUAL("!=", CheckParamAspect::isNotEqual),
        /**
         * 不为空
         */
        NOT_NULL("not null", CheckParamAspect::isNotNull);

        private String value;

        /**
         * BiFunction：接收字段值(Object)和操作数(String)，返回是否符合规则(Boolean)
         */
        private BiFunction<Object, String, Boolean> fun;

        Operator(String value, BiFunction<Object, String, Boolean> fun) {
            this.value = value;
            this.fun = fun;
        }
    }

    // -====================== 常量 =========================

    private static final String SEPARATOR = ":";

}

