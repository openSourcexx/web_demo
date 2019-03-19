package com.example.webdemo.vo.request;

import javax.validation.constraints.NotNull;

/**
 * @author tangaq
 * @date 2019/3/18
 */
public class LoginRequest {
    @NotNull(message = "账号为空")
    private String loginAccount;

    @NotNull(message = "密码为空")
    private String loginPassword;

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
