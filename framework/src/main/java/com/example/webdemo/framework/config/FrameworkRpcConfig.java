package com.example.webdemo.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.webdemo.framework.feign.RpcRequestHandlerInterceptor;
import com.example.webdemo.framework.mvc.RpcRequestHeaderMvcHandlerInterceptor;
import com.example.webdemo.framework.rpc.aop.RequestHandlerAspect;

import feign.RequestInterceptor;

/**
 * @author safe
 * @date 2021/3/21
 */
@Configuration
public class FrameworkRpcConfig {

    @Configuration
    // @EnableConfigurationProperties(FrameworkRpcProperties.class)
    public static class FrameworkRpcFeignConfiguration {
        @Bean
        public RequestInterceptor requestInterceptor() {
            return new RpcRequestHandlerInterceptor();
        }
    }

    @Bean
    public RequestHandlerAspect hsjryRequestIntegrationHandlerAspect() {
        return new RequestHandlerAspect();
    }

    @Bean
    public RequestInterceptor rpcRequestHandlerInterceptor() {
        return new RpcRequestHandlerInterceptor();
    }

    @Bean
    public HandlerInterceptor rpcRequestHeaderMvcHandlerInterceptor() {
        return new RpcRequestHeaderMvcHandlerInterceptor();
    }
}
