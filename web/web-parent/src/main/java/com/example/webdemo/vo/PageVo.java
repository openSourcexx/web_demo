package com.example.webdemo.vo;

import java.util.List;

public class PageVo<T> extends BaseVo {
    private List<T> list;

    private int total;

    public PageVo(boolean success) {
        super(success);
    }

    public PageVo(boolean success, String code) {
        super(success, code);
    }

    public PageVo(boolean success, String code, String msg) {
        super(success, code, msg);
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
