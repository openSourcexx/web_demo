package com.example.webdemo.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_user")
public class User {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 登录账号
     */
    @Column(name = "login_account")
    private String loginAccount;

    /**
     * 登录密码
     */
    @Column(name = "login_password")
    private String loginPassword;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 性别[0男1]
     */
    @Column(name = "gender")
    private String gender;

    /**
     * 手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 用户状态[0关闭1启用]
     */
    @Column(name = "user_status")
    private String userStatus;

    /**
     * 组织机构id
     */
    @Column(name = "organ_id")
    private Integer organId;

    /**
     * 登录token
     */
    @Column(name = "token")
    private String token;

    /**
     * 租户号
     */
    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "sys_id")
    private String sysId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 用户拥有角色列表
     */
    private List<Role> roleList = new ArrayList<>();

    /**
     * 用户拥有权限1对多
     */
    private List<Permission> permissionList = new ArrayList<>();

    /**
     * 用户可操作url链接
     */
    private Set<String> urlList = new HashSet<>();

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public Set<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(Set<String> urlList) {
        this.urlList = urlList;
    }

    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取登录账号
     *
     * @return login_account - 登录账号
     */
    public String getLoginAccount() {
        return loginAccount;
    }

    /**
     * 设置登录账号
     *
     * @param loginAccount 登录账号
     */
    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount == null ? null : loginAccount.trim();
    }

    /**
     * 获取登录密码
     *
     * @return login_password - 登录密码
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * 设置登录密码
     *
     * @param loginPassword 登录密码
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取性别[0男1]
     *
     * @return gender - 性别[0男1]
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别[0男1]
     *
     * @param gender 性别[0男1]
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取用户状态[0关闭1启用]
     *
     * @return user_status - 用户状态[0关闭1启用]
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * 设置用户状态[0关闭1启用]
     *
     * @param userStatus 用户状态[0关闭1启用]
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    /**
     * 获取组织机构id
     *
     * @return organ_id - 组织机构id
     */
    public Integer getOrganId() {
        return organId;
    }

    /**
     * 设置组织机构id
     *
     * @param organId 组织机构id
     */
    public void setOrganId(Integer organId) {
        this.organId = organId;
    }

    /**
     * 获取登录token
     *
     * @return token - 登录token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置登录token
     *
     * @param token 登录token
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * 获取租户号
     *
     * @return tenant_id - 租户号
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * 设置租户号
     *
     * @param tenantId 租户号
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    /**
     * @return sys_id
     */
    public String getSysId() {
        return sysId;
    }

    /**
     * @param sysId
     */
    public void setSysId(String sysId) {
        this.sysId = sysId == null ? null : sysId.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}