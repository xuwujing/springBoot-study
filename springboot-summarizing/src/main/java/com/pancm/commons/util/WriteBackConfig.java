package com.pancm.commons.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class WriteBackConfig {
	 private static final Logger LOG = LoggerFactory.getLogger(WriteBackConfig.class);
	private static volatile WriteBackConfig instance;
	
	private WriteBackConfig(){
		
	}
	
	public WriteBackConfig getInstance(){
		if(WriteBackConfig.instance == null){
			synchronized (WriteBackConfig.class) {
				if(WriteBackConfig.instance == null){
					WriteBackConfig.instance = new WriteBackConfig();
				}
			}
		}
		return WriteBackConfig.instance;
	}
	
 
	
	/**
	 * 加密回写密文
	 * 
	 * @param conf
	 *          配置文件
	 * @param confParam
	 *          回写的配置参数名
	 * @param password
	 *          明文密码
	 * @return
	 */
	public static boolean writeEncodePwd(String conf, String confParam, String password) throws Exception {
		boolean writeFlag = false;
		InputStream in = null;
		Properties prop = null;
		try {
			in = new FileInputStream(new File("application.yml"));
			prop = new SafeProperties();
			prop.load(in);
			LOG.warn("回写密文到配置文件,conf=" + conf + ",confParam=" + confParam);
			writeFlag = writeEncodePwd(conf, confParam, password, prop);
		} catch (Exception e) {
			writeFlag = false;
			LOG.error("加载jdbc.properties中的参数失败",e);
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return writeFlag;
	}
	
	/**
	 * 密文回写
	 * 
	 * @param conf
	 *          配置文件路径
	 * @param confParam
	 *          回写参数名
	 * @param password
	 *          回写之前的明文
	 * @param prop
	 *          完整内存配置对象
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private static boolean writeEncodePwd(String conf, String confParam, String password, Properties prop)
	    throws IOException, URISyntaxException {
		boolean wirteFlag = true;
		URI uri = new URI(conf.toString());
		FileOutputStream fo = null;
		Properties prop1 = (Properties) prop.clone();
		try {
			try {
				prop1.setProperty(confParam, AESUtil.encrypt(password));
			} catch (Exception e) {
				wirteFlag = false;
				LOG.error("对密码加密失败...exception:" + e.getMessage());
			}
			fo = new FileOutputStream(uri.getPath());
			prop1.store(fo, "encode password");
		} catch (IOException e) {
			wirteFlag = false;
			LOG.error(e.getMessage());
		} finally {
			if (null != fo) {
				try {
					fo.close();
					LOG.warn("回写密文完成");
				} catch (IOException e1) {
					LOG.error(e1.getMessage());
				}
			}
		}
		return wirteFlag;
	}

}

