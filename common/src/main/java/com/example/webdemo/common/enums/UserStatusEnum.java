package com.example.webdemo.common.enums;

import java.util.Arrays;

/**
 * 用户状态
 * @author tangaq
 * @date 2019/3/21
 */
public enum UserStatusEnum {
    USING("0","启用"),
    FREEZE("1","停用"),
    ON_LINE("2","在线"),
    OUT_LINE("3","离线");

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
