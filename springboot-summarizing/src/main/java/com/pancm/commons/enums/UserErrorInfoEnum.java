package com.pancm.commons.enums;

import com.pancm.commons.exception.BaseErrorInfoInterface;

/**
 * 
* @Title: UserErrorInfoEnum
* @Description: 
*   用户错误枚举类
* @Version:1.0.0  
* @author pancm
* @date 2018年3月7日
 */
public enum UserErrorInfoEnum implements BaseErrorInfoInterface{
	PARAMS_NO_COMPLETE("1001","参数不符!"),
    USER_EXIT("1002","用户退出!");
	
	private String code;
    private String message;
	
	UserErrorInfoEnum(String code, String message) {
	        this.code = code;
	        this.message = message;
	  }

	/** 
	 * 
	 */
	@Override
	public String getResultCode() {
		return this.code;
	}

	/** 
	 * 
	 */
	@Override
	public String getResultMsg() {
		return this.message;
	}

}
