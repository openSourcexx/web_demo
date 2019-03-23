package com.example.webdemo.controller;

import com.example.webdemo.beans.User;
import com.example.webdemo.service.UserService;
import com.example.webdemo.vo.PageVo;
import com.example.webdemo.vo.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/query")
    public PageVo query(@RequestBody UserRequest req) {

        return userService.query(req);
    }

    @RequestMapping("/queryAll")
    public List<User> queryAll() {

        return userService.queryAll();
    }

    @RequestMapping("/my")
    public User getById(@RequestBody User u) {
        User byId = userService.getById(u);
        return byId;
    }
}
