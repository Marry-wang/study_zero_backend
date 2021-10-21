package com.demo.backend.dto;

import lombok.Data;

/**
 * @ClassName Result
 * @Author 王孟伟
 * @Date 2021/10/21 9:57
 * @Version 1.0
 */
@Data
public class Result<T> {
    /**
     * 响应状态码
     */
    private int code;
    /**
     * 响应提示信息
     */
    private String message;
    /**
     * 响应结果对象
     */
    private T data;

    Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
