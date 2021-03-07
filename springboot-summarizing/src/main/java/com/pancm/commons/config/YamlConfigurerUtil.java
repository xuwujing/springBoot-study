package com.pancm.commons.config;

import java.util.Properties;

/**
 * @author pancm
 * @Title: springboot-study
 * @Description:
 * @Version:1.0.0
 * @Since:jdk1.8
 * @date 2021/3/7
 */
public class YamlConfigurerUtil {
    private static Properties ymlProperties = new Properties();

    public YamlConfigurerUtil(Properties properties) {
        ymlProperties = properties;
    }

    public static String getStrYmlVal(String key) {
        return ymlProperties.getProperty(key);
    }

    public static Integer getIntegerYmlVal(String key) {
        return Integer.valueOf(ymlProperties.getProperty(key));
    }
}


