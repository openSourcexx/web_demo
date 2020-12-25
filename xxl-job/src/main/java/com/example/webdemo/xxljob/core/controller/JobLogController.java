package com.example.webdemo.xxljob.core.controller;

import com.example.webdemo.xxljob.core.biz.ExecutorBiz;
import com.example.webdemo.xxljob.core.biz.model.LogParam;
import com.example.webdemo.xxljob.core.biz.model.LogParamRequest;
import com.example.webdemo.xxljob.core.biz.model.ReturnT;
import com.example.webdemo.xxljob.core.schedule.XxlJobScheduler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangaq
 * @date 2020/12/23
 */
@RestController
@RequestMapping("/api")
public class JobLogController {
    @RequestMapping("/log")
    public ReturnT<String> logDetail(@RequestBody LogParamRequest request) {
        ExecutorBiz executorBiz = XxlJobScheduler.getExecutorBiz(request.getAddressUrl());
        ReturnT<String> logResult = executorBiz.log(new LogParam(request.getLogId(), request.getTriggerTime()));
        return logResult;
    }
}
