package com.example.webdemo.service.impl;

import com.example.webdemo.beans.Role;
import com.example.webdemo.beans.RoleExample;
import com.example.webdemo.common.Page;
import com.example.webdemo.common.enums.SysCodeEnum;
import com.example.webdemo.common.exception.DBException;
import com.example.webdemo.dao.RoleMapper;
import com.example.webdemo.service.RoleService;
import com.example.webdemo.common.vo.PageVo;
import com.example.webdemo.vo.request.RoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional
    public boolean save(Role role) {
        try {
            return roleMapper.insertSelective(role) == 1;
        } catch (Exception e) {
            throw new DBException(SysCodeEnum.DB_ERR.getCode(),SysCodeEnum.DB_ERR.getDesc(),e);
        }
    }

    @Override
    public PageVo query(RoleRequest req) {
        PageVo vo = new PageVo<Role>(true);
        RoleExample example = new RoleExample();

        RoleExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(req.getId())) {
            criteria.andIdEqualTo(req.getId());
        }
        if (!StringUtils.isEmpty(req.getRoleName())) {
            criteria.andRoleNameEqualTo(req.getRoleName());
        }
        Page.pageHelp(example,req.getPageNo(),req.getPageSize());
        vo.setList(roleMapper.selectByExample(example));
        vo.setTotal(roleMapper.countByExample(example));
        return vo;
    }

    @Override
    public List<Role> queryAll() {
        return roleMapper.selectAll();
    }

    @Override
    public boolean update(Role role) {
        try {
            role.setUpdateTime(new Date());
            return roleMapper.updateByPrimaryKeySelective(role) == 1;
        } catch (Exception e) {
            throw new DBException(SysCodeEnum.DB_ERR.getCode(),SysCodeEnum.DB_ERR.getDesc(),e);
        }
    }
}
