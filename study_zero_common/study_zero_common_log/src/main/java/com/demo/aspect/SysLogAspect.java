package com.demo.aspect;

import com.demo.api.ZeroResult;
import com.demo.enums.BaseResultEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SysLogAspect {

    @Pointcut("@annotation(com.demo.annotation.SysLog)")
    public void addAdvice() {
    }

//    @Around("addAdvice()")
//    public void around(ProceedingJoinPoint joinPoint) {
//        System.out.println("Around Begin");
////        joinPoint.proceed();//执行到这里开始走进来的方法体（必须声明）
//        Object result = null;
//        try {
//            result = joinPoint.proceed();
//        } catch (Throwable e) {
//            e.printStackTrace();
//            System.out.println(e);
//        }
//        System.out.println("Around End");

//    }

    @Before("addAdvice()")
    public void before(JoinPoint joinPoint) {
        System.out.println("方法执行前");
    }

    @After("addAdvice()")
    public void after(JoinPoint joinPoint) {

        System.out.println("After Begin");
//        joinPoint.proceed();//执行到这里开始走进来的方法体（必须声明）
        //TODO 在每一个项目中进行更改，这样可以对应数据库
        System.out.println("After End");
    }

}
