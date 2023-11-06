package com.demo.ExceptionHandel;

import com.demo.api.ZeroResult;
import com.demo.exception.BaseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudayZeroExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ZeroResult handleException(BaseException e) {
        System.out.printf(e.toString());
        return ZeroResult.fail(e.getErrorCode(), e.getMessage());
    }
}
