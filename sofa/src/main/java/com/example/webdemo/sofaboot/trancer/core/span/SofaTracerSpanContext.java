package com.example.webdemo.sofaboot.trancer.core.span;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.util.CollectionUtils;

import com.alibaba.common.lang.StringUtil;

import io.opentracing.SpanContext;

/**
 * SofaTracerSpanContext
 *
 * @author tanganquan
 * @date 2020/09/25
 */
public class SofaTracerSpanContext implements SpanContext {

    public static final String RPC_ID_SEPARATOR = ".";

    private static final String TRACE_ID_KEY = "tcid";
    private static final String SPAN_ID_KEY = "spid";
    private static final String PARENT_SPAN_ID_KEY = "pspid";
    private static final String SAMPLE_KEY = "sample";
    private static final String SYS_BAGGAGE_PREFIX_KEY = "_sys_";
    private final Map<String, String> sysBaggage = new ConcurrentHashMap<>();
    /**
     * Transparent
     */
    private final Map<String, String> bizBaggage = new ConcurrentHashMap<>();
    private String traceId = StringUtil.EMPTY_STRING;
    private String spanId = StringUtil.EMPTY_STRING;
    private String parentId = StringUtil.EMPTY_STRING;
    private boolean isSampled = false;
    private AtomicInteger childContextIndex = new AtomicInteger(0);

    public SofaTracerSpanContext(String traceId, String spanId) {
        this(traceId, spanId, null, false);
    }

    public SofaTracerSpanContext(String traceId, String spanId, String parentId) {
        this(traceId, spanId, parentId, false);
    }

    public SofaTracerSpanContext(String traceId, String spanId, String parentId, boolean isSampled) {
        this.traceId = traceId;
        this.spanId = spanId;
        this.parentId = StringUtil.isBlank(parentId) ? this.getParentSpanId(spanId) : parentId;
        this.isSampled = isSampled;
    }

    public SofaTracerSpanContext() {
    }

    private String getParentSpanId(String spanId) {
        return (StringUtil.isBlank(spanId) || spanId.lastIndexOf(RPC_ID_SEPARATOR) < 0) ? StringUtil.EMPTY_STRING :
            spanId.substring(0, spanId.lastIndexOf(RPC_ID_SEPARATOR));
    }

    @Override
    public Iterable<Map.Entry<String, String>> baggageItems() {
        Map<String, String> allBaggage = new HashMap<>();
        if (!CollectionUtils.isEmpty(this.bizBaggage)) {
            allBaggage.putAll(this.bizBaggage);
        }
        if (!CollectionUtils.isEmpty(this.sysBaggage)) {
            allBaggage.putAll(this.sysBaggage);
        }
        return allBaggage.entrySet();
    }

    /**
     * Get the ID of the next sub context
     *
     * @return
     */
    public String nextChildContextId() {
        return this.spanId + RPC_ID_SEPARATOR + childContextIndex.incrementAndGet();
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
        this.parentId = this.getParentSpanId(spanId);
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public boolean isSampled() {
        return isSampled;
    }

    public void setSampled(boolean sampled) {
        isSampled = sampled;
    }

    public Map<String, String> getSysBaggage() {
        return sysBaggage;
    }

    public Map<String, String> getBizBaggage() {
        return bizBaggage;
    }

    public AtomicInteger getChildContextIndex() {
        return childContextIndex;
    }

    public void setChildContextIndex(AtomicInteger childContextIndex) {
        this.childContextIndex = childContextIndex;
    }

    public SofaTracerSpanContext addBizBaggage(Map<String, String> bizBaggage) {
        if (!CollectionUtils.isEmpty(bizBaggage)) {
            this.bizBaggage.putAll(bizBaggage);
        }
        return this;
    }

    public SofaTracerSpanContext addSysBaggage(Map<String, String> childBaggage) {
        if (!CollectionUtils.isEmpty(sysBaggage)) {
            this.sysBaggage.putAll(sysBaggage);
        }
        return this;
    }
}
