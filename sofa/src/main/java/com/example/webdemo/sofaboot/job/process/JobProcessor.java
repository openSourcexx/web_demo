package com.example.webdemo.sofaboot.job.process;

import com.example.sofaboot.job.executor.JobExecuteRequest;

/**
 * @author 唐安全
 * @date 2020/09/21
 */
public class JobProcessor extends com.example.sofaboot.job.process.AbstractJobProcessor<JobExecuteRequest> {
    public JobProcessor(com.example.sofaboot.job.process.JobProcessorFactory jobExecutorFactory) {
        jobExecutorFactory.register(this);
    }

    @Override
    protected String getExecutorType() {
        return null;
    }
}
