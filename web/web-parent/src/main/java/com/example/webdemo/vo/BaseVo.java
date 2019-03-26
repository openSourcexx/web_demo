package com.example.webdemo.vo;

import com.example.webdemo.utils.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 统一返回报文
 * @author tangaq
 * @date 2019/3/18
 */
public class BaseVo implements Serializable{
    private static final long serialVersionUID = -610002586582844862L;

    private boolean success;

    private String code;

    private String msg;

    private String time = DateUtil.getDate(new Date(),DateUtil.DEFAULT_TIME_FORMAT);

    public BaseVo(boolean success) {
        this.success = success;
    }

    public BaseVo(boolean success, String code) {
        this.success = success;
        this.code = code;
    }

    public BaseVo(boolean success, String code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
