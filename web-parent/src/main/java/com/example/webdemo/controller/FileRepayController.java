package com.example.webdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdemo.service.biz.FileRepayBiz;

/**
 * todo
 *
 * @author admin
 * @since 2019/10/8 15:50
 */
@RestController
@RequestMapping("/api/repay")
public class FileRepayController {
    @Autowired
    private FileRepayBiz fileRepayBiz;

    @RequestMapping("/batchInsert")
    public double batchInsert() {
        long s = System.currentTimeMillis();
        fileRepayBiz.batchInsert();
        long e = System.currentTimeMillis();
        System.out.println("======================cost:" + (e - s) + " ms======================");
        double v = (e - s) / 60000.00;
        System.out.println("======================cost:" + v + " s======================");
        return v;
    }
}
