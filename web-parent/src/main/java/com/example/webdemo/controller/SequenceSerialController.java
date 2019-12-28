package com.example.webdemo.controller;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdemo.common.enums.EnumSerialModalName;
import com.example.webdemo.vo.request.InitSerialReqDto;

@RestController
@RequestMapping("/api/v1")
public class SequenceSerialController {

    public static void main(String[] args) {
        System.out.println(EnumSerialModalName.ADMITTANCE_SURVEY_ID.getCode());
    }

    /**
     * 刷zk业务流水
     *
     * @return
     */
    @PostMapping("/initSerial")
    @ResponseBody
    public String initSerial(@RequestBody InitSerialReqDto request) {
        for (int i = 0; i < request.getTimes(); i++) {
            System.out.println(Thread.currentThread()
                .getName() + ",生成流水:" + request.getSerialCode() + i);
        }
        return "刷业务流水执行成功";
    }

    /**
     * 刷所有zk业务流水
     *
     * @return
     */
    @PostMapping("/initAllSerial")
    @ResponseBody
    public String initAllSerial(@RequestBody InitSerialReqDto request) {
        long l1 = System.currentTimeMillis();
        // 创建一个线程池，用10个线程处理
        Executor executor = Executors.newFixedThreadPool(10);

        for (EnumSerialModalName frs : EnumSerialModalName.values()) {
            executor.execute(() -> {
                for (int i = 0; i < request.getTimes(); i++) {
                    System.out.println(Thread.currentThread()
                        .getName() + "生成流水");
                }
            });
        }
        long l2 = System.currentTimeMillis();
        return "刷所有zk业务流水执行成功,耗时" + (l2 - l1) / 1000;
    }

}
