package com.example.webdemo.service;

import com.example.webdemo.beans.Role;
import com.example.webdemo.vo.PageVo;

import java.util.List;

public interface RoleService {
    /**
     * 添加角色
     * @param role
     * @return
     */
    boolean save(Role role);

    /**
     * 查询角色
     * @param role
     * @return
     */
    PageVo query(Role role);

    /**
     * 查询所有角色
     * @return
     */
    List<Role> queryAll();
}
