package com.example.webdemo.sofaboot.trancer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * tranceId生成
 *
 * @author tanganquan
 * @date 2020/09/24
 */
public class TraceIdGenerator {

    private static String IP_16 = "ffffffff";

    private static final int START = 1000;

    private static final int END = 9000;

    private static final int TRACE_ID_LENGTH = 30;

    private static AtomicInteger count = new AtomicInteger(START);

    static {
        try {
            String ipAddress = com.example.sofaboot.trancer.TracerUtils.getInetAddress();
            if (ipAddress != null) {
                IP_16 = getIP_16(ipAddress);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getIP_16(String ip) {
        String[] ips = ip.split(".");
        StringBuilder builder = new StringBuilder();
        for (String columns : ips) {
            String hex = Integer.toHexString(Integer.parseInt(columns));
            if (hex.length() == 1) {
                builder.append("0")
                    .append(hex);
            } else {
                builder.append(hex);
            }
        }
        return builder.toString();
    }

    public static String generate() {
        return getTraceId(IP_16, System.currentTimeMillis(), getNextId());
    }

    private static String getTraceId(String ip, long timestamp, int nextId) {
        StringBuilder appender = new StringBuilder(TRACE_ID_LENGTH);
        appender.append(ip)
            .append(timestamp)
            .append(nextId)
            .append(com.example.sofaboot.trancer.TracerUtils.getPID());
        return appender.toString();
    }

    private static int getNextId() {
        for (; ; ) {
            int current = count.get();
            int next = (current > END) ? START : current + 1;
            if (count.compareAndSet(current, next)) {
                return next;
            }
        }
    }
}
