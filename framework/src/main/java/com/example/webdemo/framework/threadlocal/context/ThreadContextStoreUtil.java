package com.example.webdemo.framework.threadlocal.context;

import java.util.Map;

/**
 * @author safe
 * @date 2021/3/5
 */
public class ThreadContextStoreUtil extends AbstractThreadContext {
    private static ThreadLocal<Map<String, Object>> threadContext = new ThreadLocal<>();
    private static volatile ThreadContextStoreUtil app = null;

    public static synchronized ThreadContextStoreUtil getInstance() {
        if (app == null) {
            Class clazz = ThreadContextStoreUtil.class;
            synchronized (clazz) {
                if (app == null) {
                    app = new ThreadContextStoreUtil();
                }
            }
        }
        return app;
    }

    @Override
    ThreadLocal<Map<String, Object>> getThreadContext() {
        return threadContext;
    }

}
