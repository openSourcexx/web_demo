package com.example.webdemo.common.vo;

/**
 * ResponseData
 */
public class ResponseData {

    private String data;
    private String sign;

    public ResponseData() {}

    public ResponseData(String data, String sign) {
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
