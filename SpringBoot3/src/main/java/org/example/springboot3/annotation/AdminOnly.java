package org.example.springboot3.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminOnly {
    /*这是一个自定义注释
    @Target(ElementType.METHOD)             作用于方法
    @Retention(RetentionPolicy.RUNTIME)     运行时生效
    AdminOnly                               @注释名字

    稍后交给AdminAspect统一定义、管理
    */
}
