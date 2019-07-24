package com.example.webdemo.common.check.validate;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.example.webdemo.common.check.annotation.Encrypt;
import com.example.webdemo.common.utils.RsaUtils;
import com.example.webdemo.common.vo.ResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 返回参数加密
 */
@Slf4j
@ControllerAdvice(basePackages = "com.example.webdemo.controller")
public class RsaEncodeResponseBodyAdvice implements ResponseBodyAdvice {

    /**
     * 服务端私钥key
     */
    @Value("${api.private.key}")
    private String privateKey;

    /**
     * 客户端公钥key
     */
    @Value("${api.public.key}")
    private String publicKey;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
        Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Encrypt encrypt = returnType.getMethodAnnotation(Encrypt.class);
        if (encrypt == null) {
            return body;
        }
        if (encrypt.outEncode()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String data = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(body);
                // 加密
                byte[] encodeData = RsaUtils.encryptData(data, publicKey);
                // 生成签名
                String resultSign = RsaUtils.rsaSign(encodeData, privateKey);
                return new ResponseData(new String(Base64.getEncoder()
                    .encode(encodeData)), resultSign);
            } catch (Exception e) {
                log.info("加密数据出现异常:{}", e.getMessage());
                e.printStackTrace();
            }
        }
        return body;
    }
}
