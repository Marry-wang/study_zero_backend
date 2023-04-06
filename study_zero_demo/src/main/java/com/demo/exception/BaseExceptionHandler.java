package com.demo.exception;

import com.demo.backend.dto.Result;
import com.demo.backend.dto.ResultCode;
import com.demo.backend.dto.ResultFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BaseExceptionHandler
 * @Author 王孟伟
 * @Date 2021/10/21 10:28
 * @Version 1.0
 */

/**
 * 开启了全局异常的捕获
 */
@Slf4j
@RestControllerAdvice
//@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value =Exception.class)
    public Result exceptionHandler(HttpServletRequest req, Exception e){
        log.info(e.getMessage());
//        return ResultFactory.buildResult(ResultCode.FAIL.code,"系统异常,请联系管理员","") ;
        return ResultFactory.buildResult(ResultCode.FAIL.code,e.getMessage(),"") ;
    }
}
