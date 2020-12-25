package com.example.webdemo.xxljob.core.biz.model;

import lombok.Data;

/**
 * @author tangaq
 * @date 2020/12/23
 */
@Data
public class LogParamRequest {
    private String addressUrl;
    private long triggerTime;
    private long logId;
}
