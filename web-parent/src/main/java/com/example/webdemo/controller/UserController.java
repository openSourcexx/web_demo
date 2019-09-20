package com.example.webdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdemo.beans.User;
import com.example.webdemo.common.vo.BaseVo;
import com.example.webdemo.common.vo.PageVo;
import com.example.webdemo.service.biz.UserRoleRelationService;
import com.example.webdemo.service.biz.UserService;
import com.example.webdemo.vo.request.AllotUserRolesRequest;
import com.example.webdemo.vo.request.UserDelRequest;
import com.example.webdemo.vo.request.UserRequest;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleRelationService userRoleRelationService;

    @RequestMapping("/query")
    public PageVo query(@RequestBody UserRequest req) {

        return userService.listUserRolesByParam(req);
    }

    @RequestMapping("/all")
    public List<User> queryAll() {
        return userService.queryAll();
    }

    @RequestMapping("/permit/my")
    public User getMenuByUid(@RequestBody User u) {
        return userService.getMenuByUid(u);
    }

    @RequestMapping("/register")
    public BaseVo register(@RequestBody User request) {
        return userService.save(request);
    }

    @RequestMapping("/remove")
    public BaseVo remove(@Valid @RequestBody UserDelRequest request) {
        return userService.remove(request.getIds());
    }

    @RequestMapping("/update")
    public BaseVo update(@RequestBody User u) {
        return userService.update(u);
    }

    @RequestMapping("/allot")
    public BaseVo allotUserRoles(@RequestBody @Valid AllotUserRolesRequest req) {
        return userRoleRelationService.allotUserRoles(req);
    }
}
