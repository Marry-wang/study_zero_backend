package com.demo.enums;

/**
 * @Author: 王孟伟
 * @Date: 2023/3/23 10:12
 * @Description:
 */
public enum BaseResultEnum {
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

    TOKENERROR(1000, "token已失效，请重新登录！"),
    TOKENNOTUSER(1001, "请求体未携带token!"),

    /**
     * user
     */
    USERNOTEXIT(2001,"用户不存在！"),

    BOOK_RECORD_IS_EXIT(2002,"此用户已经借阅次书，并且未归还！"),

    /**
     * system
     */
    SYSTEM_ROLE_IS_USER(4001,"此权限正在使用中"),

    SYSTEM_MENU_IS_USER(4002,"此菜单有子菜单，请查看后进行操作！")
    ;

    private final Integer code;

    private final String message;

    BaseResultEnum(Integer code, String message) {
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
