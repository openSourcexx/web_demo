package com.example.webdemo.xxljob.core.biz.impl;

import com.example.webdemo.xxljob.core.biz.ExecutorBiz;
import com.example.webdemo.xxljob.core.biz.model.LogParam;
import com.example.webdemo.xxljob.core.biz.model.ReturnT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tangaq
 * @date 2020/12/22
 */
public class ExecutorBizImpl implements ExecutorBiz {
    private static final Logger logger = LoggerFactory.getLogger(ExecutorBizImpl.class);

    @Override
    public ReturnT<String> log(LogParam logParam) {
        return new ReturnT<String>("=========logDetail=============");
    }
}
