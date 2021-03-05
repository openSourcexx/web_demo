package com.example.webdemo.framework.rpc.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import com.example.webdemo.framework.rpc.RpcRequest;
import com.example.webdemo.framework.threadlocal.AppParamUtil;
import com.example.webdemo.framework.threadlocal.RpcAppParamUtil;

/**
 * @author safe
 * @date 2021/3/6
 */
@Aspect
public class RequestHandlerAspect {

    @Around(value = "@annotation(com.example.webdemo.framework.rpc.RpcHandler)")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        putRpcRequest(jp);
        Object ret = jp.proceed();
        clearRpcRequest();
        return ret;
    }

    private void clearRpcRequest() {
        RpcAppParamUtil.removeRpcRequest();
    }

    private void putRpcRequest(ProceedingJoinPoint jp) {
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        // todo 获取方法上注解设置到报文
        RpcRequest rpcRequest = RpcRequest.Builder.getInstance()
            .addSerialNo(AppParamUtil.getTransSerialNo())
            .build();

        if (AppParamUtil.getRpcRequest() != null) {
            rpcRequest.setSign(AppParamUtil.getRpcRequest()
                .getSign());
        }
        RpcAppParamUtil.setRpcRequest(rpcRequest);
    }
}
