package com.pancm.result;

/**
 * 
* Title: GlobalErrorInfoEnum
* Description:
* 应用级错误码 
* Version:1.0.0  
* @author pancm
* @date 2018年3月7日
 */
public enum GlobalErrorInfoEnum implements ErrorInfoInterface{
    SUCCESS("0", "success"),
    NOT_FOUND("-1", "service not found");

    private String code;

    private String message;

    GlobalErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
