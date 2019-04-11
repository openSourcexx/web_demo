package com.example.webdemo.service.impl;

import com.example.webdemo.beans.Permission;
import com.example.webdemo.beans.User;
import com.example.webdemo.beans.UserExample;
import com.example.webdemo.common.Page;
import com.example.webdemo.common.enums.SysCodeEnum;
import com.example.webdemo.common.enums.UserStatusEnum;
import com.example.webdemo.common.exception.DBException;
import com.example.webdemo.common.vo.BaseVo;
import com.example.webdemo.common.vo.PageVo;
import com.example.webdemo.dao.PermissionMapper;
import com.example.webdemo.dao.UserMapper;
import com.example.webdemo.service.UserService;
import com.example.webdemo.vo.request.UserRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PageVo listUserRolesByParam(UserRequest req) {
        PageVo vo = new PageVo<User>(true);
        User param = new User();
        BeanUtils.copyProperties(req,param);
        int pageNo = Page.getPageStart(req.getPageNo(),req.getPageSize());
        try {
            vo.setList(userMapper.selectUserRolesByParam(param,pageNo,req.getPageSize()));
            vo.setTotal((int) userMapper.countUserRolesByParam(param,pageNo,req.getPageSize()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException(SysCodeEnum.DB_ERR.getCode(), SysCodeEnum.DB_ERR.getDesc());
        }
        return vo;
    }

    @Override
    public List<User> queryAll() {
        return userMapper.listAll();
    }

    @Override
    public User getMenuByUid(User u) {
        User user = userMapper.selectByUser(u);
        List<Permission> permissionList = permissionMapper.selectPermissionMenuByUid(user.getId());
        user.setPermissionList(permissionList);
        return user;
    }

    @Override
    public BaseVo save(User user) {
        try {
            user.setCreateTime(new Date());
            user.setUserStatus(UserStatusEnum.USING.getCode());
            return new BaseVo(userMapper.insertSelective(user) == 1);
        } catch (Exception e) {
            throw new DBException(SysCodeEnum.DB_ERR.getCode(), SysCodeEnum.DB_ERR.getDesc());
        }
    }

    @Override
    public BaseVo remove(List<Integer> ids) {
        UserExample example = new UserExample();
        example.createCriteria().andIdIn(ids);
        try {
            return new BaseVo(userMapper.deleteByExample(example) == ids.size());
        } catch (Exception e) {
            throw new DBException(SysCodeEnum.DB_ERR.getCode(), SysCodeEnum.DB_ERR.getDesc());
        }
    }

    @Override
    public BaseVo update(User user) {
        try {
            user.setUpdateTime(new Date());
            return new BaseVo(userMapper.updateByPrimaryKeySelective(user) == 1);
        } catch (Exception e) {
            throw new DBException(SysCodeEnum.DB_ERR.getCode(), SysCodeEnum.DB_ERR.getDesc());
        }
    }

}
