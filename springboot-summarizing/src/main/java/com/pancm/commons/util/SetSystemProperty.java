package com.pancm.commons.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * 更新配置文件的工具类
 */
public class SetSystemProperty {
	/**
	 * 采用静态方法
	 */
	private SafeProperties props = new SafeProperties();

	/**
	 * 根据主键key读取主键的值value
	 * 
	 * @param filePath
	 *            属性文件路径
	 * @param key
	 *            键名
	 */
	public String readValue(String profilepath, String key) {
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(profilepath));
			props.load(in);
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 更新（或插入）一对properties信息(主键及其键值) 如果该主键已经存在，更新该主键的值； 如果该主键不存在，则插件一对键值。
	 * 
	 * @param profilepath
	 *            文件路径
	 * @param keyname
	 *            键名
	 * @param keyvalue
	 *            键值
	 * @param des
	 *            说明
	 */
	public void writeProperties(String profilepath, String keyname, String keyvalue, String des) {
		try {
			// 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			OutputStream fos = new FileOutputStream(profilepath);
			props.setProperty(keyname, keyvalue);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			props.store(fos, null);
		} catch (IOException e) {
			System.err.println("writeProperties:属性文件更新错误");
		}
	}

	/**
	 * 更新properties文件的键值对 如果该主键已经存在，更新该主键的值； 如果该主键不存在，则插件一对键值。
	 * 
	 * @param profilepath
	 *            文件路径
	 * @param keyname
	 *            键名
	 * @param keyvalue
	 *            键值
	 * @param des
	 *            说明
	 */
	public void updateProperties(String profilepath, String keyname, String keyvalue, String des) {
		try {
			props.load(new FileInputStream(profilepath));
			// 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			OutputStream fos = new FileOutputStream(profilepath);
			props.setProperty(keyname, keyvalue);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			// props.store(fos, "Update '" + keyname + "' value");
			props.store(fos, des);
		} catch (IOException e) {
			System.err.println("属性文件更新错误");
		}
	}

	public void updateProperties(String profilepath, Map<String, String> map, String des) {
		InputStream in = null;
		try {
			// props.load(new FileInputStream(profilepath));
//			in = new FileInputStream(new File(profilepath));
			in = this.getClass().getClassLoader().getResourceAsStream(profilepath);
			props.load(in);
			// 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			OutputStream fos = new FileOutputStream(profilepath);
			// 遍历写入
			for (String key : map.keySet()) {
				props.setProperty(key, map.get(key));
			}
			props.store(fos, des);
		} catch (IOException e) {
			System.err.println("属性文件更新错误");
			e.printStackTrace();
		}
	}
}
