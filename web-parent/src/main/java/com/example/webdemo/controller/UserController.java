package com.example.webdemo.controller;

import com.example.webdemo.beans.User;
import com.example.webdemo.common.vo.BaseVo;
import com.example.webdemo.common.vo.PageVo;
import com.example.webdemo.service.UserService;
import com.example.webdemo.vo.request.UserDelRequest;
import com.example.webdemo.vo.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/query")
    public PageVo query(@RequestBody UserRequest req) {

        return userService.query(req);
    }

    @RequestMapping("/all")
    public List<User> queryAll() {
        return userService.queryAll();
    }

    @RequestMapping("/my")
    public User getById(@RequestBody User u) {
        return userService.getById(u);
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
}
