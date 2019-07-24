package com.example.webdemo.common.check.validate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.example.webdemo.common.check.annotation.EnumValidator;
import com.example.webdemo.common.exception.CheckException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 枚举值校验注解实现
 *
 * @author tangaq
 * @since 2019/6/19 9:37
 */
public class EnumValidatorClass implements ConstraintValidator<EnumValidator, Object> {

    private static Logger log = LoggerFactory.getLogger(EnumValidatorClass.class);

    private List<Object> enumCodes = new ArrayList<>();

    private String message;

    @Override
    public void initialize(EnumValidator enumValidator) {
        message = enumValidator.message();
        Class<?> clazz = enumValidator.value();
        Object[] objs = clazz.getEnumConstants();
        try {
            Method method = clazz.getDeclaredMethod("getCode");
            if (Objects.isNull(method)) {
                throw new CheckException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "枚举对象缺少字段名为value的字段");
            }
            Object value = null;
            for (Object obj : objs) {
                value = method.invoke(obj);
                enumCodes.add(value);
            }

        } catch (Exception e) {
            log.error("[处理枚举校验异常]", e);
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(value)) {
            return true;
        }
        return enumCodes.contains(value);
    }
}
