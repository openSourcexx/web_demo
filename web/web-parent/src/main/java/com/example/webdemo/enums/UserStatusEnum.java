package com.example.webdemo.enums;

import java.util.Arrays;

/**
 * 用户状态
 * @author tangaq
 * @date 2019/3/21
 */
public enum UserStatusEnum {
    ON_LINE("0","在线"),
    OUT_LINE("1","离线"),
    FREEZE("2","停用");

    private String code;

    private String desc;

    private UserStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static UserStatusEnum findByCode(String code) {
        return Arrays.stream(UserStatusEnum.values()).filter(e->e.getCode().equals(code))
                .findFirst().orElse(null);
    }
}
