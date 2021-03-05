package com.example.webdemo.sofaboot.trancer.core.context;

/**
 * SofaTraceContextHolder
 *
 * @author 唐安全
 * @date 2020/09/24
 */
public class SofaTraceContextHolder {
    private static final com.example.sofaboot.trancer.core.context.SofaTraceContext SOFA_TRACER_SPAN_CONTEXT
        = new com.example.sofaboot.trancer.core.context.SofaTracerThreadLocalTraceContext();

    public static com.example.sofaboot.trancer.core.context.SofaTraceContext getSofaTracerSpanContext() {
        return SOFA_TRACER_SPAN_CONTEXT;
    }
}
