package com.example.webdemo.sofaboot.job;

import lombok.Data;

/**
 * 客户端回调服务器结果
 *
 * @author 唐安全
 * @date 2020/09/22
 */
@Data
public class ClientCallbackResult extends com.example.sofaboot.job.ClientCommonResult {
    private boolean isPaused;

    public ClientCallbackResult() {
    }

    public ClientCallbackResult(boolean isSuccess, String msg, boolean isPaused) {
        setSuccess(isSuccess);
        setMsg(msg);
        this.isPaused = isPaused;
    }

    public static ClientCallbackResult buildSuccessResult(boolean isPaused) {
        return new ClientCallbackResult(true, "", isPaused);
    }

    public static ClientCallbackResult buildFailResult(String msg) {
        return new ClientCallbackResult(false, msg, true);
    }
}
