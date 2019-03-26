package com.example.webdemo.enums;

import java.util.Arrays;

/**
 * @author tangaq
 * @date 2019/3/21
 */
public enum SysCodeEnum {
    DB_ERR("000001","数据库错误"),
    ACCOUNT_OR_PASSWD_ERR("000005","账号或密码错误"),
    ACCOUNT_FREEZE("000006","账号已被停用");

    private String code;

    private String desc;

    private SysCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static SysCodeEnum findByCode(String code) {
        return Arrays.stream(SysCodeEnum.values()).filter(e->e.getCode().equals(code))
                .findFirst().orElse(null);
    }
}
