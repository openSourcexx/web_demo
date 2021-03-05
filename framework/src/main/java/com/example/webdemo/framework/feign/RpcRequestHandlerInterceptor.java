package com.example.webdemo.framework.feign;

import com.example.webdemo.framework.rpc.RpcConstant;
import com.example.webdemo.framework.rpc.RpcRequest;
import com.example.webdemo.framework.rpc.RpcRequestConvert;
import com.example.webdemo.framework.threadlocal.context.ThreadContextStoreUtil;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author safe
 * @date 2021/3/6
 */
public class RpcRequestHandlerInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header(RpcConstant.RPC_REQUEST, getRpcRequest());
    }

    private String getRpcRequest() {
        return RpcRequestConvert.encode((RpcRequest) ThreadContextStoreUtil.getInstance()
            .get(RpcConstant.RPC_REQUEST));
    }
}
