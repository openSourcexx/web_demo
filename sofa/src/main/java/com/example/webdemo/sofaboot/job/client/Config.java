package com.example.webdemo.sofaboot.job.client;

import lombok.Data;

/**
 * 初始化配置
 *
 * @author 唐安全
 * @date 2020/09/22
 */
@Data
public class Config {
    private String app;

    private String zone;

    private String instanceId = com.example.sofaboot.job.client.PropertyConstants.DEFAULT_INSTANCE_ID;

    private String accessKey;

    private String securityKey;

    public Config(String app, String zone, String instanceId) {
        this.app = app;
        this.zone = zone;
        this.instanceId = instanceId;
    }

    public Config(String app, String zone, String instanceId, String accessKey, String securityKey) {
        this.app = app;
        this.zone = zone;
        this.instanceId = instanceId;
        this.accessKey = accessKey;
        this.securityKey = securityKey;
    }
}
