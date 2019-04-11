package com.example.webdemo.common.vo;

/**
 * @author tangaq
 * @date 2019/4/11
 */
public class DetailVo<T> extends BaseVo {
    private T data;

    public DetailVo(boolean success) {
        super(success);
    }

    public DetailVo(boolean success, String code) {
        super(success, code);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
