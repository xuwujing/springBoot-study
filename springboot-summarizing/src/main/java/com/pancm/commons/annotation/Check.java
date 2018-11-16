/**
 * 
 */
package com.pancm.commons.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
* @Title: Check
* @Description: 校验的注解
* @Version:1.0.0  
* @author pancm
* @date 2018年5月10日
*/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RUNTIME)
public @interface Check {

	String[] value();

}
