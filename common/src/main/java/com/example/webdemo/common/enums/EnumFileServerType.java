package com.example.webdemo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件枚举
 *
 * @author tangaq
 * @since 2019/9/20 15:16
 */
@Getter
@AllArgsConstructor
public enum EnumFileServerType {
    /** 描述 */
    OSS("oss", "阿里云OSS"),
    SFTP("stfp", "SFTP"),
    SWIFT("swift", "对象存储"),
    KS3("ks3", "金山云OSS");;

    /** 状态码 */
    private String code;

    /** 状态描述 */
    private String description;

    /**
     * 根据编码查找枚举
     *
     * @param code 编码
     * @return {@link EnumFileServerType } 实例
     **/
    public static EnumFileServerType find(String code) {
        for (EnumFileServerType instance : EnumFileServerType.values()) {
            if (instance.getCode()
                .equals(code)) {
                return instance;
            }
        }
        return null;
    }
}