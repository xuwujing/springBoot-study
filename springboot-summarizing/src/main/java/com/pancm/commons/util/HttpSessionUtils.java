package com.pancm.commons.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * @Title: HttpSessionUtils
 * @Description: session 会话工具
 * @Version:1.0.0
 * @author pancm
 * @date 2019年1月29日
 */
public class HttpSessionUtils {

	private static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return requestAttributes.getRequest();
	}

	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 存放会话级别的值
	 * 
	 */
	public static void putObject(String requestKey, Object obj) {
		getRequest().setAttribute(requestKey, obj);
	}

	/**
	 * 根据 session key获取会话值
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getObject(String requestKey) {
		return (T) getRequest().getAttribute(requestKey);
	}

	/**
	 * 获取当前用户id
	 * 
	 */
	public static Long getCurrentAppUserId() {

		Object object = getRequest().getAttribute(APP_CURRENT_USER_ID);

		if (object != null)
			return (Long) object;

		return null;
	}

	/**
	 * 获取当前用户token
	 * 
	 * @return
	 */
	public static String getCurrentAppUserToken() {
		Object object = getRequest().getAttribute(APP_CURRENT_USER_TOKEN);

		if (object != null)
			return (String) object;

		return null;
	}

	private static String APP_CURRENT_USER_ID = "USER_ID";// 当前人id
	private static String APP_CURRENT_USER_TOKEN = "USER_TOKEN";// 当前用户token

}
