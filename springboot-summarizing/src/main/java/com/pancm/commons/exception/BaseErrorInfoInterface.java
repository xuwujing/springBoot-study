package com.pancm.commons.exception;

/**
 * 
* @Title: BaseErrorInfoInterface
* @Description: 基础接口
*  自定义的错误描述枚举类需实现该接口
* @Version:1.0.0  
* @author pancm
* @date 2018年6月25日
 */
public interface BaseErrorInfoInterface {
    /** 错误码*/
	 String getResultCode();
	
	/** 错误描述*/
	 String getResultMsg();
}
