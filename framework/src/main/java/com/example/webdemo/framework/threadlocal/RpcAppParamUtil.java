package com.example.webdemo.framework.threadlocal;

import com.example.webdemo.framework.rpc.RpcConstant;
import com.example.webdemo.framework.rpc.RpcRequest;
import com.example.webdemo.framework.threadlocal.context.ThreadContextStoreUtil;

/**
 * @author safe
 * @date 2021/3/5
 */
public class RpcAppParamUtil {
    public RpcAppParamUtil() {
    }

    /**
     * 获取RPC传递时
     *
     * @return
     */
    public static RpcRequest getRpcRequest() {
        return (RpcRequest) ThreadContextStoreUtil.getInstance()
            .get(RpcConstant.RPC_REQUEST);
    }

    public static void setRpcRequest(RpcRequest rpcRequest) {
        if (rpcRequest == null) {
            return;
        }
        ThreadContextStoreUtil.getInstance()
            .set(RpcConstant.RPC_REQUEST, rpcRequest);
    }

    public static void removeRpcRequest() {
        ThreadContextStoreUtil.getInstance()
            .removeKey(RpcConstant.RPC_REQUEST);
    }
}
