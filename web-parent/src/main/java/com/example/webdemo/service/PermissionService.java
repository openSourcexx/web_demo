package com.example.webdemo.service;

import com.example.webdemo.beans.Permission;
import com.example.webdemo.common.vo.DetailVo;
import com.example.webdemo.common.vo.PageVo;

import java.util.List;

public interface PermissionService {
    PageVo query(Permission p);

    List<Permission> queryAll();

    DetailVo getChildrenByParentId(Permission p);
}
