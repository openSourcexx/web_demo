package com.example.webdemo.sofaboot.rpc;

/**
 * 基于ThreadLocal的内部使用的上下文传递。一般存在于：客户端请求线程、服务端业务线程池、客户端异步线程
 *
 * @author 唐安全
 * @date 2020/09/18
 */
public class RpcInternalContext implements Cloneable {
    /**
     * The constant LOCAL
     */
    private static final ThreadLocal<RpcInternalContext> LOCAL = new ThreadLocal<>();

    public static RpcInternalContext getContext() {
        RpcInternalContext context = LOCAL.get();
        if (context == null) {
            context = new RpcInternalContext();
            LOCAL.set(context);
        }
        return context;
    }

    /**
     * 设置上下文
     *
     * @param context RPC内置上下文
     */
    public static void setContext(RpcInternalContext context) {
        LOCAL.set(context);
    }

    public static RpcInternalContext peekContext() {
        return LOCAL.get();
    }

    public static void removeContext() {
        LOCAL.remove();
    }
}
