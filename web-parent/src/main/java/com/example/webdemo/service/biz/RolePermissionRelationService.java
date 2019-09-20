package com.example.webdemo.service.biz;

import java.util.List;

import com.example.webdemo.beans.RolePermissionRelation;

public interface RolePermissionRelationService {
    /**
     * 查询角色关联的权限
     * @param roleId
     * @return
     */
    List<RolePermissionRelation> queryByRoleId(Integer roleId);
}
