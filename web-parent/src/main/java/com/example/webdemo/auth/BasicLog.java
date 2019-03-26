package com.example.webdemo.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志注解
 * @author tangaq
 * @date 2019/3/21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface BasicLog {

    /**
     * @return 日志值
     */
    String value() default "";
}
