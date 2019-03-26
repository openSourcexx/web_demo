package com.example.webdemo.vo.response;

import com.example.webdemo.beans.OperatorLog;
import com.example.webdemo.common.vo.BaseVo;

import java.util.List;

/**
 * @author tangaq
 * @date 2019/3/21
 */
public class OperatorLogVo extends BaseVo {
    private int total;

    private List<OperatorLog> list;

    public OperatorLogVo(boolean success) {
        super(success);
    }

    public OperatorLogVo(boolean success, String code) {
        super(success, code);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<OperatorLog> getList() {
        return list;
    }

    public void setList(List<OperatorLog> list) {
        this.list = list;
    }
}
