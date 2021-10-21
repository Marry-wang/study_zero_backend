package com.demo.backend.dto;

/**
 * @ClassName ResultFactory
 * @Author 王孟伟
 * @Date 2021/10/21 10:07
 * @Version 1.0
 */
public class ResultFactory {

    public static Result buildSuccess(Object data){
        return buidResult(ResultCode.SUCCESS,"成功",data);
    }

    public static Result buidResult(ResultCode resultCode, String message, Object data) {
        return buildResult(resultCode.code, message, data);
    }

    public static Result buildResult(int code ,String message ,Object data){
        return new Result(code,message,data);
    }
}
