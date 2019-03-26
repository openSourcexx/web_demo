package com.example.webdemo.controller;

import com.example.webdemo.beans.OperatorLog;
import com.example.webdemo.service.OperatorLogService;
import com.example.webdemo.vo.BaseVo;
import com.example.webdemo.vo.request.OperatorLogRequest;
import com.example.webdemo.vo.response.OperatorLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangaq
 * @date 2019/3/21
 */
@RestController
@RequestMapping("/log")
public class OperatorLogController {

    @Autowired
    private OperatorLogService operatorLogService;

    @RequestMapping("/save")
    public BaseVo save(@RequestBody OperatorLog log) {
        return new BaseVo(operatorLogService.save(log));
    }

    @RequestMapping("/query")
    public OperatorLogVo query(@RequestBody OperatorLogRequest log) {
        return operatorLogService.query(log);
    }
}
