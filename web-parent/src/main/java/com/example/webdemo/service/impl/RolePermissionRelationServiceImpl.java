package com.example.webdemo.service.impl;

import com.example.webdemo.beans.RolePermissionRelation;
import com.example.webdemo.beans.example.UserRoleRelationExample;
import com.example.webdemo.dao.RolePermissionRelationMapper;
import com.example.webdemo.service.biz.RolePermissionRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionRelationServiceImpl implements RolePermissionRelationService {
    @Autowired
    private RolePermissionRelationMapper relationMapper;

    @Override
    public List<RolePermissionRelation> queryByRoleId(Integer roleId) {
        UserRoleRelationExample.RolePermissionRelationExample example = new UserRoleRelationExample.RolePermissionRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return relationMapper.selectByExample(example);
    }
}
