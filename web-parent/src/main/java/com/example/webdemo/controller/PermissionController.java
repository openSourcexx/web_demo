package com.example.webdemo.controller;

import com.example.webdemo.beans.Permission;
import com.example.webdemo.common.vo.DetailVo;
import com.example.webdemo.service.PermissionService;
import com.example.webdemo.common.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/permit")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/query")
    public PageVo query(@RequestBody Permission p) {
        return permissionService.query(p);
    }

    @RequestMapping("/allTree")
    public PageVo queryAllTree() {
        PageVo<Permission> vo = new PageVo<>(true);
        vo.setList(permissionService.queryAllTree());
        return vo;
    }

    @RequestMapping("/all")
    public PageVo queryAll() {
        return permissionService.queryAll();
    }


    @RequestMapping("/detail")
    public DetailVo getChildrenByParentId(@RequestBody Permission p) {
        return permissionService.getChildrenByParentId(p);
    }
}
