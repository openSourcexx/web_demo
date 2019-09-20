package com.example.webdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdemo.auth.BasicLog;
import com.example.webdemo.common.vo.BaseVo;
import com.example.webdemo.service.biz.LoginService;
import com.example.webdemo.vo.request.LoginRequest;

import javax.validation.Valid;

/**
 * @author tangaq
 * @date 2019/3/18
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    @BasicLog("登陆")
    public BaseVo login(@RequestBody @Valid LoginRequest request) {
        return loginService.login(request);
    }

}
