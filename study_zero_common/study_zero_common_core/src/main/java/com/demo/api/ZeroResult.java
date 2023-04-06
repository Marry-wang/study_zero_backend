package com.demo.api;

import com.demo.base.BaseResult;
import com.demo.enums.ZeroResultEnum;

/**
 * @Author: 王孟伟
 * @Date: 2023/3/23 10:06
 * @Description:
 */
public class ZeroResult<T> extends BaseResult<T> {

    private ZeroResult() {

    }

    public ZeroResult(ZeroResultEnum zeroResultEnum) {
        setCode(zeroResultEnum.getCode());
        setMessage(zeroResultEnum.getMessage());
    }

    public ZeroResult(ZeroResultEnum zeroResultEnum, T data) {
        setCode(zeroResultEnum.getCode());
        setMessage(zeroResultEnum.getMessage());
        setData(data);
    }

    public ZeroResult(Integer code, String message) {
        setCode(code);
        setMessage(message);
    }

    public static <T> ZeroResult<T> success() {
        return new ZeroResult<T>(ZeroResultEnum.SUCCESS);
    }

    public static <T> ZeroResult<T> success(T data) {
        return new ZeroResult<T>(ZeroResultEnum.SUCCESS, data);
    }

    public static <T> ZeroResult<T> success(Integer code, String message) {
        return new ZeroResult<T>(code, message);
    }

    public static <T> ZeroResult<T> error() {
        return new ZeroResult<T>(ZeroResultEnum.ERROR);
    }

    public static <T> ZeroResult<T> error(ZeroResultEnum zeroResultEnum) {
        return new ZeroResult<T>(zeroResultEnum);
    }

    public static <T> ZeroResult<T> error(Integer code, String message) {
        return new ZeroResult<T>(code, message);
    }

    public static <T> ZeroResult<T> error(ZeroResultEnum zeroResultEnum, T data) {
        return new ZeroResult<T>(zeroResultEnum, data);
    }

    public static <T> ZeroResult<T> fail() {
        return new ZeroResult<T>(ZeroResultEnum.FAIL);
    }

    public static <T> ZeroResult<T> fail(ZeroResultEnum zeroResultEnum) {
        return new ZeroResult<T>(zeroResultEnum);
    }

    public static <T> ZeroResult<T> fail(Integer code, String message) {
        return new ZeroResult<T>(code, message);
    }

    public static <T> ZeroResult<T> fail(ZeroResultEnum zeroResultEnum, T data) {
        return new ZeroResult<T>(zeroResultEnum, data);
    }
}
