package com.example.webdemo.vo.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AllotUserRolesRequest {
    /**
     * 用户id
     */
    @NotNull(message = "用户id为空")
    private Integer uid;

    /**
     * 角色id
     */
    @NotEmpty(message = "角色为空")
    @NotNull(message = "角色为空")
    private List<Integer> roleIds;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }
}
