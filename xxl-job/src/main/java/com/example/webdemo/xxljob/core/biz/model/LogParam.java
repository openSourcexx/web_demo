package com.example.webdemo.xxljob.core.biz.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tangaq
 * @date 2020/12/22
 */
@Data
public class LogParam implements Serializable {
    private static final long serialVersionUID = -7307076881479236669L;

    private int jobId;
    private String executorHandler;
    private String executorParams;
    private String executorBlockStrategy;
    private int executorTimeout;

    private long logId;
    private long logDateTime;

    public LogParam() {
    }

    public LogParam(long logId, long logDateTime) {
        this.logId = logId;
        this.logDateTime = logDateTime;
    }
}
