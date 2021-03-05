package com.example.webdemo.framework.threadlocal.context;

import java.util.HashMap;
import java.util.Map;

/**
 * @author safe
 * @date 2021/3/5
 */
public abstract class AbstractThreadContext {
    public AbstractThreadContext() {
    }

    abstract ThreadLocal<Map<String, Object>> getThreadContext();

    public void set(final String key, final Object value) {
        ThreadLocal<Map<String, Object>> threadContext = this.getThreadContext();
        Map<String, Object> map = threadContext.get();
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(key, value);
        if (value instanceof String) {
            map.put((String) value, Thread.currentThread()
                .getName());
        }
        threadContext.set(map);
    }

    public void removeKey(String key) {
        ThreadLocal<Map<String, Object>> threadContext = this.getThreadContext();
        Map<String, Object> map = threadContext.get();
        if (map == null) {
            map = new HashMap<>();
        }
        map.remove(key);
        threadContext.set(map);
    }

    public Object get(String key) {
        ThreadLocal<Map<String, Object>> threadContext = this.getThreadContext();
        Map<String, Object> map = threadContext.get();
        return map == null ? null : map.get(key);
    }

    public void clean() {
        ThreadLocal<Map<String, Object>> threadContext = this.getThreadContext();
        Map<String, Object> map = threadContext.get();
        if (map == null) {
            map.clear();
            threadContext.set(map);
        }
    }
}
