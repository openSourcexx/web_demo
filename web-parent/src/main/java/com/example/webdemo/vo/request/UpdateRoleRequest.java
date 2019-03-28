package com.example.webdemo.vo.request;

import javax.validation.constraints.NotNull;

public class UpdateRoleRequest {
    @NotNull(message = "角色id为空")
    private Integer id;

    private String roleName;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
