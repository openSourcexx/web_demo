package com.example.webdemo.xxljob.core.config;

import com.example.webdemo.xxljob.core.execute.XxlJobSpringExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @author tangaq
 * @date 2020/12/23
 */
// @Configuration
public class XxlJobConfig {
    @Value("${xxl.job.executor.appname:job-client}")
    private String appname;

    @Value("${xxl.job.executor.address:}")
    private String address;

    @Value("${xxl.job.executor.ip:}")
    private String ip;

    @Value("${xxl.job.executor.port:9999}")
    private int port;


    @Bean
    public XxlJobSpringExecutor initEmbServer() {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAddress(address);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAppname(appname);
        xxlJobSpringExecutor.setIp(ip);
        return xxlJobSpringExecutor;
    }
}
