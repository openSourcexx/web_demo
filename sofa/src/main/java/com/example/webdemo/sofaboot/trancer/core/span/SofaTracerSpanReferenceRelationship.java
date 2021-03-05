package com.example.webdemo.sofaboot.trancer.core.span;

/**
 * SofaTracerSpanReferenceRelationship
 * <p>
 * {@link io.opentracing.References}
 * </p>
 *
 * @author tanganquan
 * @date 2020/09/25
 */
public class SofaTracerSpanReferenceRelationship {
    private com.example.sofaboot.trancer.core.span.SofaTracerSpanContext sofaTracerSpanContext;

    /**
     * {@link io.opentracing.References}
     */
    private String referenceType;

    public SofaTracerSpanReferenceRelationship(
        com.example.sofaboot.trancer.core.span.SofaTracerSpanContext sofaTracerSpanContext, String referenceType) {
        this.sofaTracerSpanContext = sofaTracerSpanContext;
        this.referenceType = referenceType;
    }

    public com.example.sofaboot.trancer.core.span.SofaTracerSpanContext getSofaTracerSpanContext() {
        return sofaTracerSpanContext;
    }

    public void setSofaTracerSpanContext(
        com.example.sofaboot.trancer.core.span.SofaTracerSpanContext sofaTracerSpanContext) {
        this.sofaTracerSpanContext = sofaTracerSpanContext;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }
}
