package com.example.webdemo.service;

import com.example.webdemo.beans.Permission;
import com.example.webdemo.vo.PageVo;

import java.util.List;
import java.util.Set;

public interface PermissionService {
    PageVo query(Permission p);

    List<Permission> queryAll();

}
