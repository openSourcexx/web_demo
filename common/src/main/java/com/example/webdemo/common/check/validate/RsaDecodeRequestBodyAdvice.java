package com.example.webdemo.common.check.validate;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.example.webdemo.common.check.annotation.Encrypt;
import com.example.webdemo.common.utils.GsonUtil;
import com.example.webdemo.common.utils.RsaUtils;
import com.example.webdemo.common.vo.RequestData;

import lombok.extern.slf4j.Slf4j;

/**
 * 请求参数解密
 *
 * @author tangaq
 * @version V2.1
 * @since 2.1.0 2019/5/21 15:52
 */
@Slf4j
@ControllerAdvice(basePackages = "com.example.webdemo.controller")
public class RsaDecodeRequestBodyAdvice implements RequestBodyAdvice {

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
    public boolean supports(MethodParameter methodParameter, Type targetType,
        Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
        Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return new HttpInputMessage() {
            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }

            @Override
            public InputStream getBody() throws IOException {
                Encrypt encrypt = parameter.getMethodAnnotation(Encrypt.class);
                if (encrypt == null) {
                    return inputMessage.getBody();
                }
                if (encrypt.inDecode()) {
                    String content = IOUtils.toString(inputMessage.getBody(), StandardCharsets.UTF_8.name());
                    RequestData requestData = GsonUtil.json2Obj(content, RequestData.class);
                    try {
                        // 验签
                        boolean verify = RsaUtils.checkSign(requestData.getData(), requestData.getSign(), publicKey);
                        // 解密
                        if (verify) {
                            String decryptData = RsaUtils.decryptData(requestData.getData(), privateKey);
                            return IOUtils.toInputStream(decryptData, StandardCharsets.UTF_8.name());
                        } else {
                            log.info("验证签名失败:{}", verify);
                        }
                    } catch (Exception e) {
                        log.info("解密数据出现异常:{}", e.getMessage());
                        e.printStackTrace();
                    }
                }
                return inputMessage.getBody();
            }
        };
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
        Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
        Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
