package com.example.webdemo.sofaboot.job;

import java.io.Serializable;

import lombok.Data;

/**
 * @author 唐安全
 * @date 2020/09/22
 */
@Data
public class ClientCommonResult implements Serializable {
    /**
     * 是否成功
     */
    private boolean isSuccess;

    /**
     * 是否结束
     */
    private boolean isEnd = false;
    /**
     * 结果信息
     */
    private String msg;

    public ClientCommonResult() {
    }

    public ClientCommonResult(boolean isSuccess, boolean isEnd, String msg) {
        this.isSuccess = isSuccess;
        this.isEnd = isEnd;
        this.msg = msg;
    }

    /**
     * 构建成功结果
     *
     * @return
     */
    public static ClientCommonResult buildSuccessResult() {
        return new ClientCommonResult(true, false, "");
    }

    /**
     * 构建成功结果
     *
     * @param isEnd
     * @return
     */
    public static ClientCommonResult buildSuccessResult(boolean isEnd) {
        return new ClientCommonResult(true, isEnd, "");
    }

    /**
     * 构建失败结果
     *
     * @param msg
     * @return
     */
    public static ClientCommonResult buildSuccessResult(String msg) {
        return new ClientCommonResult(false, false, msg);
    }
}
