package com.example.webdemo.service;

import com.example.webdemo.beans.RolePermissionRelation;

import java.util.List;

public interface RolePermissionRelationService {
    /**
     * 查询角色关联的权限
     * @param roleId
     * @return
     */
    List<RolePermissionRelation> queryByRoleId(Integer roleId);
}
