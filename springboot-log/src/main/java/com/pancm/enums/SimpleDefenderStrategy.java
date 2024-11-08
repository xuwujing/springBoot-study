package com.pancm.enums;

public enum SimpleDefenderStrategy implements DefenderStrategy {

    /** 姓名类脱敏策略 */
    NAME("name", 1, 1, '*'),

    /** 账号类脱敏策略 */
    ACCOUNT_NO("accountNo", 2, 2, '*'),

    /** 邮箱类脱敏策略 */
    EMAIL("email", 2, 7, '*'),

    /** 身份证号类脱敏策略 */
    ID_CARD("idCard", 6, 4, '*'),

    /** 手机号码类脱敏策略 */
    PHONE_NUMBER("phoneNumber", 3, 4, '*'),

    /** 住址类脱敏策略 */
    ADDRESS("address", 3, 4, '*');

    // 省略...
}