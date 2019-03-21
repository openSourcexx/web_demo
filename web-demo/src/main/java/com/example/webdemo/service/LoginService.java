package com.example.webdemo.service;

import com.example.webdemo.beans.User;
import com.example.webdemo.vo.BaseVo;
import com.example.webdemo.vo.request.LoginRequest;

import javax.validation.Valid; /**
 * @author tangaq
 * @date 2019/3/18
 */
public interface LoginService {
    BaseVo register(LoginRequest request);

    BaseVo login(@Valid LoginRequest request);
}
