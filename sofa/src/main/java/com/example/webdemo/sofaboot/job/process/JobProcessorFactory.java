package com.example.webdemo.sofaboot.job.process;

import java.util.ArrayList;
import java.util.List;

/**
 * job请求工厂
 *
 * @author 唐安全
 * @date 2020/09/21
 */
public class JobProcessorFactory {
    private List<com.example.sofaboot.job.process.SyncUserProcessor> jobProcessors = new ArrayList<>();

    public void register(com.example.sofaboot.job.process.SyncUserProcessor jobProcessor) {
        jobProcessors.add(jobProcessor);
    }

    public List<com.example.sofaboot.job.process.SyncUserProcessor> getJobProcessors() {
        return jobProcessors;
    }
}
