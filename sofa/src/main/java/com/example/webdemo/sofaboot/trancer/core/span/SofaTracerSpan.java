package com.example.webdemo.sofaboot.trancer.core.span;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.sofaboot.trancer.core.SofaTracer;

import io.opentracing.Span;
import io.opentracing.SpanContext;

/**
 * SofaTracerSpan
 *
 * @author 唐安全
 * @date 2020/09/24
 */
public class SofaTracerSpan implements Span {
    private String operationName;

    private List<com.example.sofaboot.trancer.core.span.SofaTracerSpanReferenceRelationship> references;

    private final SofaTracer sofaTracer;

    private final com.example.sofaboot.trancer.core.span.SofaTracerSpanContext sofaTracerSpanContext;

    private SofaTracerSpan parentSofaTracerSpan = null;

    private long startTime;
    private long endTime = -1;

    public SofaTracerSpan(SofaTracer sofaTracer, long begin,
        List<com.example.sofaboot.trancer.core.span.SofaTracerSpanReferenceRelationship> spanReferences,
        String operationName, com.example.sofaboot.trancer.core.span.SofaTracerSpanContext sofaTracerSpanContext,
        Map<String, Object> tags) {
        this.sofaTracer = sofaTracer;
        this.startTime = startTime;
        this.references = spanReferences != null ? new ArrayList<>(spanReferences) : null;
        this.operationName = operationName;
        this.sofaTracerSpanContext = sofaTracerSpanContext;
        this.setTags(tags);
    }

    private void setTags(Map<String, ?> tags) {
        // tdo
    }

    @Override
    public SpanContext context() {
        return this.sofaTracerSpanContext;
    }

    @Override
    public void finish() {

    }

    @Override
    public void finish(long l) {

    }

    @Override
    public void close() {

    }

    @Override
    public Span setTag(String s, String s1) {
        return null;
    }

    @Override
    public Span setTag(String s, boolean b) {
        return null;
    }

    @Override
    public Span setTag(String s, Number number) {
        return null;
    }

    @Override
    public Span log(Map<String, ?> map) {
        return null;
    }

    @Override
    public Span log(long l, Map<String, ?> map) {
        return null;
    }

    @Override
    public Span log(String s) {
        return null;
    }

    @Override
    public Span log(long l, String s) {
        return null;
    }

    @Override
    public Span setBaggageItem(String s, String s1) {
        return null;
    }

    @Override
    public String getBaggageItem(String s) {
        return null;
    }

    @Override
    public Span setOperationName(String s) {
        return null;
    }

    @Override
    public Span log(String s, Object o) {
        return null;
    }

    @Override
    public Span log(long l, String s, Object o) {
        return null;
    }

    public com.example.sofaboot.trancer.core.span.SofaTracerSpanContext getSofaTracerSpanContext() {
        return sofaTracerSpanContext;
    }

    public SofaTracerSpan getParentSofaTracerSpan() {
        return parentSofaTracerSpan;
    }

    public void setParentSofaTracerSpan(SofaTracerSpan parentSofaTracerSpan) {
        this.parentSofaTracerSpan = parentSofaTracerSpan;
    }
}
