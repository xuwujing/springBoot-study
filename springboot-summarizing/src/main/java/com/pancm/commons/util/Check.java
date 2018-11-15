/**
 * 
 */
package com.pancm.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
* @Title: Check
* @Description: 
* @Version:1.0.0  
* @author pancm
* @date 2018年5月10日
*/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RUNTIME)
public @interface Check {

	String[] value();

}
