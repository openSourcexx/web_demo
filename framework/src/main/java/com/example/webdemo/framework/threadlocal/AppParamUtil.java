package com.example.webdemo.framework.threadlocal;

import com.example.webdemo.framework.rpc.RpcConstant;
import com.example.webdemo.framework.rpc.RpcRequest;
import com.example.webdemo.framework.threadlocal.context.ThreadContextStoreUtil;

/**
 * @author safe
 * @date 2021/3/6
 */
public class AppParamUtil {
    /**
     * 设置报文头
     *
     * @param rpcRequest
     */
    public static void setRequest(RpcRequest rpcRequest) {
        if (rpcRequest == null) {
            return;
        }
        ThreadContextStoreUtil.getInstance()
            .set(RpcConstant.RPC_REQUEST, rpcRequest);
    }

    /**
     * 获取报文头
     *
     * @return
     */
    public static RpcRequest getRpcRequest() {
        return (RpcRequest) ThreadContextStoreUtil.getInstance()
            .get(RpcConstant.RPC_REQUEST);
    }

    /**
     * 清理报文头
     */
    public static void removeRequest() {
        ThreadContextStoreUtil.getInstance()
            .removeKey(RpcConstant.RPC_REQUEST);
    }

    /**
     * 获取交易流水号
     *
     * @return
     */
    public static String getTransSerialNo() {
        return (String) ThreadContextStoreUtil.getInstance()
            .get(RpcConstant.TRANS_SERIAL_NO);
    }
}
