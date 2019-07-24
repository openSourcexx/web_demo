package com.example.webdemo.common.vo;

/**
 * RequestData
 */
public class RequestData {

    private String data;
    private String sign;

    public RequestData() {}

    public RequestData(String data, String sign) {
        this.data = data;
        this.sign = sign;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}
