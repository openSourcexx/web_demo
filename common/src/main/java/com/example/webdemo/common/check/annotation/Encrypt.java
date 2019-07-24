package com.example.webdemo.common.check.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.Mapping;

/**
 * 解密注解
 *
 * @author tangaq
 * @version V2.1
 * @since 2.1.0 2019/5/21 15:39
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
@Documented
public @interface Encrypt {

    /**
     * 入参是否解密,默认解密
     *
     * @return
     */
    boolean inDecode() default true;

    /**
     * 出参是否加密,默认加密
     *
     * @return
     */
    boolean outEncode() default true;

}
