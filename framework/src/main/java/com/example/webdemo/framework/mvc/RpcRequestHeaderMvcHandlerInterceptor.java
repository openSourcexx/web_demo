package com.example.webdemo.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.webdemo.framework.rpc.RpcConstant;
import com.example.webdemo.framework.rpc.RpcRequestConvert;
import com.example.webdemo.framework.threadlocal.AppParamUtil;

/**
 * @author safe
 * @date 2021/3/6
 */
public class RpcRequestHeaderMvcHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception {
        String rpcRequest = request.getHeader(RpcConstant.RPC_REQUEST);
        if (StringUtils.isBlank(rpcRequest)) {
            throw new RuntimeException("报文头缺失");
        }
        AppParamUtil.setRequest(RpcRequestConvert.decode(rpcRequest));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    throws Exception {
        AppParamUtil.removeRequest();
    }
}
