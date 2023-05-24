package com.demo.api;

import com.demo.base.BaseResult;
import com.demo.enums.BaseResultEnum;

/**
 * @Author: 王孟伟
 * @Date: 2023/3/23 10:06
 * @Description:
 */
public class ZeroResult<T> extends BaseResult<T> {

    private ZeroResult() {

    }

    public ZeroResult(BaseResultEnum baseResultEnum) {
        setCode(baseResultEnum.getCode());
        setMessage(baseResultEnum.getMessage());
    }

    public ZeroResult(BaseResultEnum baseResultEnum, T data) {
        setCode(baseResultEnum.getCode());
        setMessage(baseResultEnum.getMessage());
        setData(data);
    }

    public ZeroResult(Integer code, String message) {
        setCode(code);
        setMessage(message);
    }

    public static <T> ZeroResult<T> success() {
        return new ZeroResult<T>(BaseResultEnum.SUCCESS);
    }

    public static <T> ZeroResult<T> success(T data) {
        return new ZeroResult<T>(BaseResultEnum.SUCCESS, data);
    }

    public static <T> ZeroResult<T> success(Integer code, String message) {
        return new ZeroResult<T>(code, message);
    }

    public static <T> ZeroResult<T> error() {
        return new ZeroResult<T>(BaseResultEnum.ERROR);
    }

    public static <T> ZeroResult<T> error(BaseResultEnum baseResultEnum) {
        return new ZeroResult<T>(baseResultEnum);
    }

    public static <T> ZeroResult<T> error(Integer code, String message) {
        return new ZeroResult<T>(code, message);
    }

    public static <T> ZeroResult<T> error(BaseResultEnum baseResultEnum, T data) {
        return new ZeroResult<T>(baseResultEnum, data);
    }

    public static <T> ZeroResult<T> fail() {
        return new ZeroResult<T>(BaseResultEnum.FAIL);
    }

    public static <T> ZeroResult<T> fail(BaseResultEnum baseResultEnum) {
        return new ZeroResult<T>(baseResultEnum);
    }

    public static <T> ZeroResult<T> fail(Integer code, String message) {
        return new ZeroResult<T>(code, message);
    }

    public static <T> ZeroResult<T> fail(BaseResultEnum baseResultEnum, T data) {
        return new ZeroResult<T>(baseResultEnum, data);
    }
}
