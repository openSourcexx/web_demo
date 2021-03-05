package com.example.webdemo.sofaboot.rpc.event;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.alipay.sofa.rpc.context.AsyncRuntime;
import com.example.sofaboot.common.RpcConfigs;
import com.example.sofaboot.rpc.RpcInternalContext;

/**
 * 为内部事件传输使用的简单事件
 *
 * @author 唐安全
 * @date 2020/10/09
 */
public class EventBus {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventBus.class);

    private static final boolean EVENT_BUS_ENABLE = RpcConfigs.getBooleanValue("event.bus.enable");

    /** 某中事件的订阅者 */
    private final static ConcurrentMap<Class<? extends Event>, CopyOnWriteArraySet<Subscriber>> SUBSCRIBER_MAP
        = new ConcurrentHashMap<>();

    public static void post(final Event event) {
        if (isEnable()) {
            return;
        }
        CopyOnWriteArraySet<Subscriber> subscribers = SUBSCRIBER_MAP.get(event.getClass());
        if (!CollectionUtils.isEmpty(subscribers)) {
            for (Subscriber subscriber : subscribers) {
                if (subscriber.isSync()) {
                    handleEvent(subscriber, event);
                } else { // 异步
                    final RpcInternalContext context = RpcInternalContext.peekContext();
                    final ThreadPoolExecutor asyncThreadPool = AsyncRuntime.getAsyncThreadPool();
                    try {
                        asyncThreadPool.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    RpcInternalContext.setContext(context);
                                    handleEvent(subscriber, event);
                                } finally {
                                    RpcInternalContext.removeContext();
                                }
                            }
                        });
                    } catch (Exception e) {
                        LOGGER.warn("This queue is full when post event to async execute, queue size is "
                            + asyncThreadPool.getQueue()
                            .size() + ", please optimize this async thread pool of eventbus.");
                    }
                }
            }
        }
    }

    private static void handleEvent(final Subscriber subscriber, final Event event) {
        try {
            subscriber.onEvent(event);
        } catch (Exception e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Handle " + event.getClass() + " error", e);
            }
        }
    }

    public static boolean isEnable() {
        return EVENT_BUS_ENABLE;
    }
}
