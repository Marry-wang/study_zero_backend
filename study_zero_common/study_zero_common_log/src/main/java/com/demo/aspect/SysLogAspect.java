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

    @Pointcut("execution(public * com.demo.*.controller.*.*(..)) && @annotation(com.demo.annotation.SysLog)")
    public void addAdvice() {
    }

    @Around("addAdvice()")
    public Object around(ProceedingJoinPoint joinPoint) {
        System.out.println("Around Begin");
//        joinPoint.proceed();//执行到这里开始走进来的方法体（必须声明）
        Object result = null;
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            String deviceId = (String) args[0];
            if (!"03".equals(deviceId)) {
                return "no anthorization";
            }
        }
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            return ZeroResult.error(BaseResultEnum.ERROR,"系统异常，请联系管理员");
        }
        System.out.println("Around End");
        return result;

    }

    @Before("addAdvice()")
    public void before(JoinPoint joinPoint) {
        System.out.println("方法执行前");
    }

    @After("addAdvice()")
    public void after() {
        System.out.println("方法执行完");
    }

}
