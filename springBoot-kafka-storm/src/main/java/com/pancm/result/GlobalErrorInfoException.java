package com.pancm.result;

/**
 * 
* Title: GlobalErrorInfoException
* Description:
* 统一错误码 
* Version:1.0.0  
* @author pancm
* @date 2018年3月7日
 */
public class GlobalErrorInfoException extends Exception {

   
	private static final long serialVersionUID = 4054725154208973897L;
	private ErrorInfoInterface errorInfo;

    public GlobalErrorInfoException (ErrorInfoInterface errorInfo) {
        this.errorInfo = errorInfo;
    }

    public ErrorInfoInterface getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfoInterface errorInfo) {
        this.errorInfo = errorInfo;
    }
}
