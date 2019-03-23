package com.example.webdemo.controller;

import com.example.webdemo.beans.Permission;
import com.example.webdemo.beans.Role;
import com.example.webdemo.beans.RolePermissionRelation;
import com.example.webdemo.service.PermissionService;
import com.example.webdemo.service.RolePermissionRelationService;
import com.example.webdemo.service.RoleService;
import com.example.webdemo.vo.BaseVo;
import com.example.webdemo.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionRelationService rolePermissionRelationService;

    @RequestMapping("/save")
    public BaseVo saveRole(@RequestBody Role role) {
        return new BaseVo(roleService.save(role));
    }

    @RequestMapping("/query")
    public PageVo query(@RequestBody Role role) {
        return roleService.query(role);
    }

    @RequestMapping("/queryAll")
    public List<Role> queryAll() {
        return roleService.queryAll();
    }

}
