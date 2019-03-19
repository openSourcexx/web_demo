package com.example.webdemo.controller;

import com.example.webdemo.beans.User;
import com.example.webdemo.service.LoginService;
import com.example.webdemo.vo.BaseVo;
import com.example.webdemo.vo.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public BaseVo login(@RequestBody @Valid LoginRequest request) {
        User u = loginService.login(request);
        if (u == null) {
            return new BaseVo(false,"账号或密码错误");
        }
        return new BaseVo(true);
    }

    @RequestMapping("/register")
    public BaseVo register(@RequestBody LoginRequest request) {
        return loginService.register(request);
    }
}
