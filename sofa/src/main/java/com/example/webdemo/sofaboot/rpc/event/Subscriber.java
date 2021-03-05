package com.example.webdemo.sofaboot.rpc.event;

/**
 * 事件订阅
 *
 * @author 唐安全
 * @date 2020/10/10
 */
public abstract class Subscriber {
    /** 接到事件是否同步执行 */
    protected boolean sync = true;

    protected Subscriber() {
    }

    public Subscriber(boolean sync) {
        this.sync = sync;
    }

    /** 是否同步 */
    public boolean isSync() {
        return sync;
    }

    /** 事件处理，请处理异常 */
    public abstract void onEvent(Event event);
}
