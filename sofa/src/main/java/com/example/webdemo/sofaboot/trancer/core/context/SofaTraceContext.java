package com.example.webdemo.sofaboot.trancer.core.context;

import com.example.sofaboot.trancer.core.span.SofaTracerSpan;

/**
 * SofaTraceContext allows an application access and manipulation of the current span state
 *
 * @author 唐安全
 * @date 2020/09/24
 */
public interface SofaTraceContext {
    /**
     * add TracerContext
     * 如果为空则不添加
     *
     * @param sofaTracerSpan
     */
    void push(SofaTracerSpan sofaTracerSpan);

    SofaTracerSpan getCurrentSpan();

    SofaTracerSpan pop();

    int getThreadLocalSpanSize();

    void clear();

    boolean isEmpty();
}
