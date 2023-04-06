package com.demo.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ZeroAnnotation {
    /**
     * @Documented 注解
     *     功能：指明修饰的注解，可以被例如javadoc此类的工具文档化，只负责标记，没有成员取值。
     */
    /**
     * @Retention 注解
     * 功能：指明修饰的注解的生存周期，即会保留到哪个阶段。
     * RetentionPolicy的取值包含以下三种：
     * SOURCE：源码级别保留，编译后即丢弃。
     * CLASS:编译级别保留，编译后的class文件中存在，在jvm运行时丢弃，这是默认值。
     * RUNTIME： 运行级别保留，编译后的class文件中存在，在jvm运行时保留，可以被反射调用。
     */
    /**
     * @Target 注解
     * 功能：指明了修饰的这个注解的使用范围，即被描述的注解可以用在哪里。
     * ElementType的取值包含以下几种：
     *
     * TYPE:类，接口或者枚举
     * FIELD:域，包含枚举常量
     * METHOD:方法
     * PARAMETER:参数
     * CONSTRUCTOR:构造方法
     * LOCAL_VARIABLE:局部变量
     * ANNOTATION_TYPE:注解类型
     * PACKAGE:包
     */
}
