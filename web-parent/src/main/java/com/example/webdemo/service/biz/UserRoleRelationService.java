package com.example.webdemo.service.biz;

import com.example.webdemo.common.vo.BaseVo;
import com.example.webdemo.vo.request.AllotUserRolesRequest;

public interface UserRoleRelationService {
    /**
     * 用户分配角色
     * @param req
     * @return
     */
    BaseVo allotUserRoles(AllotUserRolesRequest req);
}
