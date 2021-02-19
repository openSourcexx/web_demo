package com.example.webdemo.common.utils.sequence;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import com.example.webdemo.common.utils.IPUtil;

/**
 * 雪花算法流水号生成工具
 */
public class SequenceTool {

    // 机器标识位数
    private final static long workerIdBits = 5L;
    // 数据中心标识位数
    private final static long datacenterIdBits = 5L;
    private static Sequence sequence = null;

    static {
        new SequenceTool();
    }

    private SequenceTool() {

        //获取机器编码
        long workerId = IPUtil.getMachineNum();
        //获取进程编码
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        long processId = Long.parseLong(runtimeMXBean.getName()
            .split("@")[0]);

        //避免编码超出最大值
        //服务器掩码
        long workerMask = ~(-1L << workerIdBits);
        workerId = workerId & workerMask;
        //进程掩码
        long processMask = ~(-1L << datacenterIdBits);
        processId = processId & processMask;
        sequence = new Sequence(workerId, processId);
    }

    public static synchronized String nextId() {
        return String.valueOf(sequence.nextId());
    }
}


