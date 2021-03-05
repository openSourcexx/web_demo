package com.example.webdemo.sofaboot.trancer.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.example.sofaboot.trancer.TraceIdGenerator;
import com.example.sofaboot.trancer.core.reporter.Reporter;
import com.example.sofaboot.trancer.core.sampler.Sampler;
import com.example.sofaboot.trancer.core.span.SofaTracerSpan;
import com.example.sofaboot.trancer.core.span.SofaTracerSpanContext;
import com.example.sofaboot.trancer.core.span.SofaTracerSpanReferenceRelationship;

import io.opentracing.References;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import lombok.Data;

/**
 * SofaTracer
 *
 * @author 唐安全
 * @date 2020/09/24
 */
@Data
public class SofaTracer implements Tracer {
    /**
     * normal root spanId's default value
     */
    private static final String ROOT_SPAN_ID = "0";

    private final String tracerType;

    private final Reporter serverReporter;

    private final Reporter clientReporter;

    private final Sampler sampler;

    private final Map<String, Object> tracerTags = new ConcurrentHashMap<>();

    private long startTime = -1;

    public SofaTracer(String tracerType, Reporter serverReporter, Reporter clientReporter, Sampler sampler,
        Map<String, Object> tracerTags) {
        this.tracerType = tracerType;
        this.serverReporter = serverReporter;
        this.clientReporter = clientReporter;
        this.sampler = sampler;
        if (tracerTags != null && tracerTags.size() > 0) {
            this.tracerTags.putAll(tracerTags);
        }
    }

    public SofaTracer(String tracerType, Reporter serverReporter, Reporter clientReporter, Sampler sampler) {
        this.tracerType = tracerType;
        this.serverReporter = serverReporter;
        this.clientReporter = clientReporter;
        this.sampler = sampler;
    }

    @Override
    public SpanBuilder buildSpan(String s) {
        return null;
    }

    @Override
    public <C> void inject(SpanContext spanContext, Format<C> format, C c) {

    }

    @Override
    public <C> SpanContext extract(Format<C> format, C c) {
        return null;
    }

    public class SofaTracerSpanBuilder implements SpanBuilder {

        private final Map<String, Object> tags = new HashMap<>();
        private String operationName;
        private long startTime = -1;
        private List<SofaTracerSpanReferenceRelationship> references = Collections.emptyList();

        public SofaTracerSpanBuilder(String operationName) {
            this.operationName = operationName;
        }

        @Override
        public SpanBuilder asChildOf(SpanContext parent) {
            return addReference(References.CHILD_OF, parent);
        }

        @Override
        public SpanBuilder asChildOf(Span parentSpan) {
            if (parentSpan == null) {
                return this;
            }
            return addReference(References.CHILD_OF, parentSpan.context());
        }

        @Override
        public SpanBuilder addReference(String referenceType, SpanContext referenceContext) {
            if (referenceContext == null) {
                return this;
            }
            if (!(referenceContext instanceof SofaTracerSpanContext)) {
                return this;
            }
            if (!References.CHILD_OF.equals(referenceType) && !References.FOLLOWS_FROM.equals(referenceType)) {
                return this;
            }
            if (references.isEmpty()) {
                references = Collections.singletonList(
                    new SofaTracerSpanReferenceRelationship((SofaTracerSpanContext) referenceContext, tracerType));
            } else {
                if (references.size() == 1) {
                    references = new ArrayList<>(references);
                }
                references.add(
                    new SofaTracerSpanReferenceRelationship((SofaTracerSpanContext) referenceContext, referenceType));
            }
            return this;
        }

        @Override
        public SpanBuilder withTag(String key, String value) {
            this.tags.put(key, value);
            return this;
        }

        @Override
        public SpanBuilder withTag(String key, boolean value) {
            this.tags.put(key, value);
            return this;
        }

        @Override
        public SpanBuilder withTag(String key, Number value) {
            this.tags.put(key, value);
            return this;
        }

        @Override
        public SpanBuilder withStartTimestamp(long microseconds) {
            this.startTime = microseconds;
            return this;
        }

        @Override
        public Span start() {
            SofaTracerSpanContext sofaTracerSpanContext;
            if (this.references != null && this.references.size() > 0) {
                sofaTracerSpanContext = this.createChildContext();
            } else {
                sofaTracerSpanContext = this.createRootSpanContext();
            }
            long begin = this.startTime > 0 ? this.startTime : System.currentTimeMillis();
            SofaTracerSpan sofaTracerSpan = new SofaTracerSpan(SofaTracer.this, begin, this.references,
                this.operationName, sofaTracerSpanContext, this.tags);

            return sofaTracerSpan;
        }

        private SofaTracerSpanContext createRootSpanContext() {
            String traceId = TraceIdGenerator.generate();
            return new SofaTracerSpanContext(traceId, ROOT_SPAN_ID, StringUtils.EMPTY);
        }

        private SofaTracerSpanContext createChildContext() {
            SofaTracerSpanContext preferredReference = preferredReference();

            SofaTracerSpanContext sofaTracerSpanContext = new SofaTracerSpanContext(preferredReference.getTraceId(),
                preferredReference.nextChildContextId(), preferredReference.getSpanId(),
                preferredReference.isSampled());
            sofaTracerSpanContext.addBizBaggage(this.createChildBaggage(true));
            sofaTracerSpanContext.addSysBaggage(this.createChildBaggage(false));
            return sofaTracerSpanContext;
        }

        private Map<String, String> createChildBaggage(boolean isBiz) {
            if (references.size() == 1) {
                if (isBiz) {
                    return references.get(0)
                        .getSofaTracerSpanContext()
                        .getBizBaggage();
                } else {
                    return references.get(0)
                        .getSofaTracerSpanContext()
                        .getSysBaggage();
                }
            }
            Map<String, String> baggage = null;
            for (SofaTracerSpanReferenceRelationship reference : references) {
                Map<String, String> referenceBaggage;
                if (isBiz) {
                    referenceBaggage = reference.getSofaTracerSpanContext()
                        .getSysBaggage();
                } else {
                    referenceBaggage = reference.getSofaTracerSpanContext()
                        .getSysBaggage();
                }
                if (!CollectionUtils.isEmpty(referenceBaggage)) {
                    if (baggage == null) {
                        baggage = new HashMap<>();
                    }
                    baggage.putAll(referenceBaggage);
                }
            }
            return baggage;
        }

        private SofaTracerSpanContext preferredReference() {
            SofaTracerSpanReferenceRelationship preferredReference = references.get(0);
            for (SofaTracerSpanReferenceRelationship reference : references) {
                // childOf takes precedence as a preferred parent
                String referenceType = reference.getReferenceType();
                if (References.CHILD_OF.equals(referenceType) && !References.CHILD_OF.equals(
                    preferredReference.getReferenceType())) {
                    preferredReference = reference;
                    break;
                }
            }
            return preferredReference.getSofaTracerSpanContext();
        }
    }

}
