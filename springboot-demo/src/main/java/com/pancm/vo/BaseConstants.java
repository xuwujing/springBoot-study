package com.pancm.vo;

/**
 * @Author pancm
 * @Description
 * @Date  2019/2/17
 * @Param
 * @return
 **/
public class BaseConstants {

    public static String CONSTANT_MODULE_TYPE_STRING = "string";
    public static String CONSTANT_MODULE_TYPE_INT = "int";

    public static String SEPARATOR_SPACE = " ";
    public static String SEPARATOR_COMMA = ", ";
    public static String SEPARATOR_DOT = ".";
    public static String SEPARATOR_LINE = "\n";

    public static int DATE_RANGE_ARRAY_SIZE = 2;

    /**
     * excel 相关
     */
    public static final int EXCEL_MAX_EMPTY_HEADER_COUNT = 50;
    public static final int EXCEL_ROW_TYPE_SKIP = 1;
    public static final int EXCEL_ROW_TYPE_NORMAL = 0;

    public static final String IP_SPLIT_SEPARATOR = "\\.";

    /**
     * 校验类，合法的mac地址长度
     */
    public static int VALID_MAC_LENGTH = 17;
    public static int VALID_MIN_MAC_LENGTH = 12;
    public static int VALID_IP_MAX_LENGTH = 15;

    public static int IP_ASSIGN_LAST = 254;

    public static int CODE_ERROR_UNKNOWN = 5000;
    public static int CODE_SUCCESS = 0;
}
