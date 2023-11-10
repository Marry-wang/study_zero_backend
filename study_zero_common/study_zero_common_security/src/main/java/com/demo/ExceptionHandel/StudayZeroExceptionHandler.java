package com.demo.ExceptionHandel;

import com.demo.api.ZeroResult;
import com.demo.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
public class StudayZeroExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ZeroResult handleException(BaseException e) {
        System.out.printf(e.toString());
        return ZeroResult.fail(e.getErrorCode(), e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ZeroResult handleException(Exception e) {
        log.error(e.getMessage(),e);
        return ZeroResult.fail(5001,e.getMessage());
    }
}
