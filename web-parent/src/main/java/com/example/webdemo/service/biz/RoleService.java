package com.example.webdemo.service.biz;

import java.util.List;

import com.example.webdemo.beans.Role;
import com.example.webdemo.common.vo.DetailVo;
import com.example.webdemo.common.vo.PageVo;
import com.example.webdemo.vo.request.RoleRequest;

public interface RoleService {
    /**
     * 添加角色
     * @param role
     * @return
     */
    boolean save(Role role);

    /**
     * 分页查询角色
     * @param role
     * @return
     */
    PageVo query(RoleRequest role);

    /**
     * 查询所有角色
     * @return
     */
    List<Role> queryAll();

    /**
     * 修改角色
     * @param role
     * @return
     */
    boolean update(Role role);

    DetailVo getByRid(Integer id);
}
