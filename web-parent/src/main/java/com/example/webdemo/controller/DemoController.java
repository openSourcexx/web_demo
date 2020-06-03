package com.example.webdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdemo.service.biz.DemoBiz;

/**
 * demo
 *
 * @author admin
 * @since 2019/9/25 16:16
 */
@RestController
@RequestMapping("/api")
public class DemoController {
    @Autowired
    private DemoBiz demoBiz;

    @RequestMapping("/demo")
    public void demo() {
        demoBiz.testRpcConn();
    }

    @RequestMapping("/b")
    public void compensate() {
        demoBiz.compensate();
    }

    @RequestMapping("/c")
    public void otherDb() {
        demoBiz.otherDb();
    }
}
