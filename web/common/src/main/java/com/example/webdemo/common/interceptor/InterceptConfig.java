package com.example.webdemo.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author tangaq
 * @date 2019/3/21
 */
@Configuration
public class InterceptConfig implements WebMvcConfigurer{

    /**
     * 装配拦截器
     * @param registry 拦截器注册中心
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthCheckInterceptor()).addPathPatterns("/**");
    }
}
