package com.example.webdemo.service;

import com.example.webdemo.beans.User;
import com.example.webdemo.beans.UserExample;
import com.example.webdemo.dao.UserMapper;
import com.example.webdemo.vo.BaseVo;
import com.example.webdemo.vo.request.LoginRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @author tangaq
 * @date 2019/3/18
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public BaseVo register(LoginRequest request) {
        User u = new User();
        BeanUtils.copyProperties(request,u);
        return new BaseVo(userMapper.insert(u) == 1);
    }

    @Override
    public User login(@Valid LoginRequest request) {
        User u = new User();
        BeanUtils.copyProperties(request,u);
        return userMapper.login(u);
    }
}
