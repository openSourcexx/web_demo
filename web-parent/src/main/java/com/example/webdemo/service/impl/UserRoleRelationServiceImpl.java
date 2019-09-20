package com.example.webdemo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webdemo.beans.UserRoleRelation;
import com.example.webdemo.common.enums.SysCodeEnum;
import com.example.webdemo.common.exception.DBException;
import com.example.webdemo.common.vo.BaseVo;
import com.example.webdemo.dao.UserRoleRelationMapper;
import com.example.webdemo.service.biz.UserRoleRelationService;
import com.example.webdemo.vo.request.AllotUserRolesRequest;

@Service
public class UserRoleRelationServiceImpl implements UserRoleRelationService {
    private Logger logger = LoggerFactory.getLogger(UserRoleRelationServiceImpl.class);

    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;

    @Override
    public BaseVo allotUserRoles(AllotUserRolesRequest req) {
        try {
            List<UserRoleRelation> list = new ArrayList<>();
            UserRoleRelation urr = null;
            for (Integer roleId : req.getRoleIds()) {
                urr = new UserRoleRelation();
                urr.setUserId(req.getUid());
                urr.setRoleId(roleId);
                urr.setCreateTime(new Date());
                list.add(urr);
            }
            return new BaseVo(userRoleRelationMapper.insertBatch(list) == req.getRoleIds().size());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException(SysCodeEnum.DB_ERR.getCode(),SysCodeEnum.DB_ERR.getDesc());
        }
    }
}
