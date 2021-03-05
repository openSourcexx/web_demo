package com.example.webdemo.sofaboot.trancer.core.context;

import com.example.sofaboot.trancer.core.span.SofaTracerSpan;

/**
 * SofaTracerThreadLocalTraceContext
 *
 * @author tanganquan
 * @date 2020/09/25
 */
public class SofaTracerThreadLocalTraceContext implements com.example.sofaboot.trancer.core.context.SofaTraceContext {
    private final ThreadLocal<SofaTracerSpan> threadLocal = new ThreadLocal<>();

    @Override
    public void push(SofaTracerSpan span) {
        if (span == null) {
            return;
        }
        threadLocal.set(span);
    }

    @Override
    public SofaTracerSpan getCurrentSpan() {
        if (this.isEmpty()) {
            return null;
        }
        return threadLocal.get();
    }

    @Override
    public SofaTracerSpan pop() {
        if (this.isEmpty()) {
            return null;
        }
        SofaTracerSpan sofaTracerSpan = threadLocal.get();
        // remove
        this.clear();
        return sofaTracerSpan;
    }

    @Override
    public int getThreadLocalSpanSize() {
        SofaTracerSpan sofaTracerSpan = threadLocal.get();
        return sofaTracerSpan == null ? 0 : 1;
    }

    @Override
    public void clear() {
        threadLocal.remove();
    }

    @Override
    public boolean isEmpty() {
        SofaTracerSpan sofaTracerSpan = threadLocal.get();
        return sofaTracerSpan == null;
    }
}
