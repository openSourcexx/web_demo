package com.example.webdemo.vo.request;

import com.example.webdemo.common.Page;

public class UserRequest extends Page {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 登陆账号
     */
    private String loginAccount;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
