package com.pancm.constant;

import com.pancm.result.ErrorInfoInterface;

/**
 * 
* Title: UserErrorInfoEnum
* Description: 
* 用户表错误案例
* Version:1.0.0  
* @author pancm
* @date 2018年3月7日
 */
public enum UserErrorInfoEnum implements ErrorInfoInterface{
	PARAMS_NO_COMPLETE("001","params no complete"),
    USER_EXIT("002","user exit");
	
	private String code;
    private String message;
	
	UserErrorInfoEnum(String code, String message) {
	        this.code = code;
	        this.message = message;
	  }
	
	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
