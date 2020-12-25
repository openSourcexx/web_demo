package com.example.webdemo.xxljob.core.biz;

import com.example.webdemo.xxljob.core.biz.model.LogParam;
import com.example.webdemo.xxljob.core.biz.model.ReturnT;

/**
 * @author tangaq
 * @date 2020/12/22
 */
public interface ExecutorBiz {
    ReturnT<String> log(LogParam logParam);
}
