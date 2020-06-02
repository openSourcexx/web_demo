package com.example.webdemo.common.enums;

/**
 * 异步业务类型
 */
public enum EnumCompensateType {
    DEMO_A("a", "测试1"),
    DEMO_B("b", "测试2"),
    ;

    /**
     * 状态码
     **/
    private String code;
    /**
     * 状态描述
     **/
    private String description;

    /**
     * 构造方法
     *
     * @param code 状态码
     * @param description 描述
     */
    EnumCompensateType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static EnumCompensateType find(String code) {
        for (EnumCompensateType type : EnumCompensateType.values()) {
            if (type.getCode()
                .equals(code)) {
                return type;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
