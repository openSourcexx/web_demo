package com.example.webdemo.service.impl;

import com.example.webdemo.beans.RolePermissionRelation;
import com.example.webdemo.beans.RolePermissionRelationExample;
import com.example.webdemo.dao.RolePermissionRelationMapper;
import com.example.webdemo.service.RolePermissionRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionRelationServiceImpl implements RolePermissionRelationService {
    @Autowired
    private RolePermissionRelationMapper relationMapper;

    @Override
    public List<RolePermissionRelation> queryByRoleId(Integer roleId) {
        RolePermissionRelationExample example = new RolePermissionRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return relationMapper.selectByExample(example);
    }
}
