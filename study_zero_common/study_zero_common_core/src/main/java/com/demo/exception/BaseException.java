package com.demo.exception;

import com.demo.enums.BaseResultEnum;

/**
 * @author wangmengwei
 */
public class BaseException extends RuntimeException{

    private Integer errorCode;

    private String message;


    public BaseException(Integer errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;

    }

    public BaseException(BaseResultEnum baseResultEnum) {
        super(baseResultEnum.getMessage());
        this.errorCode = baseResultEnum.getCode();
        this.message = baseResultEnum.getMessage();

    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
