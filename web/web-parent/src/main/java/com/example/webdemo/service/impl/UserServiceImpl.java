package com.example.webdemo.service.impl;

import com.example.webdemo.beans.Permission;
import com.example.webdemo.beans.User;
import com.example.webdemo.beans.UserExample;
import com.example.webdemo.dao.PermissionMapper;
import com.example.webdemo.dao.UserMapper;
import com.example.webdemo.service.UserService;
import com.example.webdemo.common.vo.PageVo;
import com.example.webdemo.vo.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PageVo query(UserRequest req) {
        PageVo vo = new PageVo<User>(true);
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(req.getLoginAccount())) {
            criteria.andLoginAccountEqualTo(req.getLoginAccount());
        }

        if (!StringUtils.isEmpty(req.getUserName())) {
            criteria.andUserNameEqualTo(req.getUserName());
        }

        if (!StringUtils.isEmpty(req.getMobile())) {
            criteria.andMobileEqualTo(req.getMobile());
        }

        List<User> userList = userMapper.selectByExample(example);
        vo.setList(userList);
        vo.setTotal(userMapper.countByExample(example));
        return vo;
    }

    @Override
    public List<User> queryAll() {
        return userMapper.listAll();
    }

    @Override
    public User getById(User u) {
        User user = userMapper.selectByUser(u);
        List<Permission> permissionList = permissionMapper.selectPermissionMenuByUid(user.getId());
        user.setPermissionList(permissionList);
        return user;
    }

}
