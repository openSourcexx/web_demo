package com.example.webdemo.sofaboot.job.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;

import com.example.sofaboot.job.handler.IJobHandler;

/**
 * todo
 *
 * @author 唐安全
 * @date 2020/09/23
 */
public class SchedulerClientInitializer implements InitializingBean {
    public static final String APP_NAME_KEY = "spring.application.name";
    public static final String ZONE_KEY = "com.demo.ldc.zone";
    private static final String ANTVIP_END_POINT = "com.demo.antvip.endpoint";
    private static final String INSTANCE_ID_NAME = "com.demo.instanceid";
    public static com.example.sofaboot.job.client.AntSchedulerClient antSchedulerClient;
    private static Object initLock = new Object();
    private String zone;
    private String app;
    private String instanceId;
    private String antvipEndpoint;
    private List<IJobHandler> handlerList = new ArrayList<>();
    private String accessKey;
    private String securityKey;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        antvipEndpoint = System.getProperty(ANTVIP_END_POINT);
        if (StringUtils.isEmpty(antvipEndpoint)) {
            //            antvipEndpoint = Slite2Configuration.getProperty(ANTVIP_END_POINT);
        }

        Config config = new Config(app, zone, instanceId, accessKey, securityKey);
        synchronized (initLock) {
            antSchedulerClient = new com.example.sofaboot.job.client.AntSchedulerClient(config);
            antSchedulerClient.doInit();
        }
    }
}
