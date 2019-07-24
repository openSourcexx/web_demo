package com.example.webdemo.common.check.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.webdemo.common.check.validate.EnumValidatorClass;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 枚举值校验注解
 *
 * @author tangaq@yunrong.cn
 * @since 2019/6/19 9:36
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = EnumValidatorClass.class)
public @interface EnumValidator {
    Class<?> value();

    String message() default "入参值不在正确枚举中";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
