package com.example.webdemo.sofaboot.job.process;

import com.alipay.remoting.BizContext;

/**
 * @author 唐安全
 * @date 2020/09/21
 */
public abstract class SyncUserProcessor<T> {
    public abstract Object handleRequest(BizContext bizCtx, T request) throws Exception;
}
