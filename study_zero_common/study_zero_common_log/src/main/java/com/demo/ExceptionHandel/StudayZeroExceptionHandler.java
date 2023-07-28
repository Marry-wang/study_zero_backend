package com.demo.ExceptionHandel;

import com.demo.api.ZeroResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudayZeroExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ZeroResult handleException(Exception e) {
        System.out.printf(e.toString());
        return ZeroResult.fail(5001,e.getMessage());
    }
}
