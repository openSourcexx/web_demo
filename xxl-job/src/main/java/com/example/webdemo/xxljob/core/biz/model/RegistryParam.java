package com.example.webdemo.xxljob.core.biz.model;

import java.io.Serializable;

/**
 * @author tangaq
 * @date 2020/12/22
 */
public class RegistryParam implements Serializable {
    private static final long serialVersionUID = -2576852327682930892L;

    private String registryGroup;
    private String registryKey;
    private String registryValue;

    public RegistryParam() {
    }

    public RegistryParam(String registryGroup, String registryKey, String registryValue) {
        this.registryGroup = registryGroup;
        this.registryKey = registryKey;
        this.registryValue = registryValue;
    }

    public String getRegistryGroup() {
        return registryGroup;
    }

    public void setRegistryGroup(String registryGroup) {
        this.registryGroup = registryGroup;
    }

    public String getRegistryKey() {
        return registryKey;
    }

    public void setRegistryKey(String registryKey) {
        this.registryKey = registryKey;
    }

    public String getRegistryValue() {
        return registryValue;
    }

    public void setRegistryValue(String registryValue) {
        this.registryValue = registryValue;
    }
}
