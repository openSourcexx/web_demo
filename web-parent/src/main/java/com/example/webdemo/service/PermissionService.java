package com.example.webdemo.service;

import com.example.webdemo.beans.Permission;
import com.example.webdemo.common.vo.DetailVo;
import com.example.webdemo.common.vo.PageVo;

import java.util.List;

public interface PermissionService {
    PageVo query(Permission p);

    /**
     * 查询所有权限树
     * @return 权限树
     */
    List<Permission> queryAllTree();

    /**
     * 查询所有权限
     * @return
     */
    PageVo queryAll();

    /**
     * 查询子权限树
     * @param p
     * @return
     */
    DetailVo getChildrenByParentId(Permission p);


}
