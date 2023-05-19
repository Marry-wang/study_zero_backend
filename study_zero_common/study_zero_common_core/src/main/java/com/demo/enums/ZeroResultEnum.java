package com.demo.enums;

/**
 * @Author: 王孟伟
 * @Date: 2023/3/23 10:12
 * @Description:
 */
public enum ZeroResultEnum {
    /**
     * 成功
     */
    SUCCESS(200, "success"),

    /**
     * 异常
     */
    ERROR(400, "error"),

    /**
     * 失败
     */
    FAIL(900, "fail"),

    /**
     * system
     */
    SYSTEM_ROLE_IS_USER(4001,"此权限正在使用中")
    ;

    private final Integer code;

    private final String message;

    ZeroResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
