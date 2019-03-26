package com.example.webdemo.service.impl;

import com.example.webdemo.beans.User;
import com.example.webdemo.beans.UserExample;
import com.example.webdemo.dao.UserMapper;
import com.example.webdemo.enums.SysCodeEnum;
import com.example.webdemo.enums.UserStatusEnum;
import com.example.webdemo.exception.ServiceException;
import com.example.webdemo.service.LoginService;
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
        return new BaseVo(userMapper.insertSelective(u) == 1);
    }

    @Override
    public BaseVo login(LoginRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request,user);

        User u = userMapper.selectByUser(user);
        if (u == null) {
            throw new ServiceException(SysCodeEnum.ACCOUNT_OR_PASSWD_ERR.getCode(),SysCodeEnum.ACCOUNT_OR_PASSWD_ERR.getDesc());
        }

        if (UserStatusEnum.FREEZE.getCode().equals(u.getUserStatus())) {
            throw new ServiceException(SysCodeEnum.ACCOUNT_FREEZE.getCode(),SysCodeEnum.ACCOUNT_FREEZE.getDesc());
        }
        return new BaseVo(true);
    }
}
