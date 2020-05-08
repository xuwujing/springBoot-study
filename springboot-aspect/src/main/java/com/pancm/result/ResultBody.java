package com.pancm.result;

import com.alibaba.fastjson.JSONObject;

/**
 *
 * @Title: ResultBody
 * @Description: 返回格式
 * @Version:1.0.0
 * @author pancm
 * @date 2018年3月7日
 */
public class ResultBody {
	/**
	 * 响应代码
	 */
	private String code;

	/**
	 * 响应消息
	 */
	private String message;

	/**
	 * 响应结果
	 */
	private String result;

	public ResultBody() {
	}



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
