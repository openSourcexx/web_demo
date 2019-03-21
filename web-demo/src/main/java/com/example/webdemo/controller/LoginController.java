package com.example.webdemo.controller;

import com.example.webdemo.auth.BasiLog;
import com.example.webdemo.beans.User;
import com.example.webdemo.enums.SysCodeEnum;
import com.example.webdemo.enums.UserStatusEnum;
import com.example.webdemo.exception.ServiceException;
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
    @BasiLog("登陆")
    public BaseVo login(@RequestBody @Valid LoginRequest request) {
        return loginService.login(request);
    }

    @RequestMapping("/register")
    public BaseVo register(@RequestBody LoginRequest request) {
        return loginService.register(request);
    }
}
