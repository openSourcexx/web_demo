package com.example.webdemo.service.impl;

import com.example.webdemo.beans.Permission;
import com.example.webdemo.beans.example.PermissionExample;
import com.example.webdemo.common.vo.DetailVo;
import com.example.webdemo.common.vo.PageVo;
import com.example.webdemo.dao.PermissionMapper;
import com.example.webdemo.service.biz.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PageVo query(Permission p) {
        PageVo vo = new PageVo<Permission>(true);
        PermissionExample example = new PermissionExample();
        example.setOrderByClause("update_time desc");
        vo.setList(permissionMapper.selectByExample(example));
        vo.setTotal(permissionMapper.countByExample(example));
        return vo;
    }

    @Override
    public List<Permission> queryAllTree() {
        return permissionMapper.selectAllTree();
    }

    @Override
    public DetailVo getChildrenByParentId(Permission p) {
        DetailVo<Permission> vo = new DetailVo<>(true);
        vo.setData(permissionMapper.selectChildrenById(p.getId()));
        return vo;
    }

    @Override
    public PageVo queryAll() {
        PageVo<Permission> vo = new PageVo<>(true);
        vo.setList(permissionMapper.selectAll());
        PermissionExample example = new PermissionExample();
        vo.setTotal(permissionMapper.countByExample(example));
        return vo;
    }

}
