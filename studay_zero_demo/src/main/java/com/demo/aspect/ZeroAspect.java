package com.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName ZeroAspect
 * @Author 王孟伟
 * @Date 2021/10/18 10:17
 * @Version 1.0
 */
@Aspect
@Component
public class ZeroAspect {
    /**
     * Pointcut 是指那些方法需要被执行"AOP",是由"Pointcut Expression"来描述的.
     * Pointcut可以有下列方式来定义或者通过&& || 和!的方式进行组合.
     * args()
     * @args()
     * execution()
     * this()
     * target()
     * @target()
     * within()
     * @within()
     * @annotation
     * 其中execution 是用的最多的,其格式为:
     * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern)throws-pattern?)
     * returning type pattern,name pattern, and parameters pattern是必须的.
     * ret-type-pattern:可以为表示任何返回值,全路径的类名等.
     * name-pattern:指定方法名,代表所以,set,代表以set开头的所有方法.
     * parameters pattern:指定方法参数(声明的类型),(…)代表所有参数,()代表一个参数,(*,String)代表第一个参数为任何值,第二个为String类型.
     *
     * 几个注解的解释
     * @Around 环绕切点, 在进入切点前, 跟切点后执行
     *
     * @After 在切点后, return前执行,
     *
     * @Before 在切点前执行方法, 内容为指定的切点
     * @Aspect 将一个类定义为一个切面类
     */

    @Pointcut("execution(public * com.demo.controller.*.*(..)) && @annotation(com.demo.annotation.ZeroAnnotation)")
    public void addAdvice(){}

    @Around("addAdvice()")
    public Object  around(ProceedingJoinPoint joinPoint){
        System.out.println("Around Begin");
//        joinPoint.proceed();//执行到这里开始走进来的方法体（必须声明）
        Object result = null;
        Object[] args = joinPoint.getArgs();
        if(args != null && args.length >0) {
            String deviceId = (String) args[0];
            if(!"03".equals(deviceId)) {
                return "no anthorization";
            }
        }
        try {
            result =joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Around End");
        return result;

    }

    @Before("addAdvice()")
    public void before(JoinPoint joinPoint){
        System.out.println("方法执行前");
    }

    @After("addAdvice()")
    public void after(){
        System.out.println("方法执行完");
    }



}
