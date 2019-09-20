package com.example.webdemo.service.biz;

import java.util.List;

import com.example.webdemo.beans.User;
import com.example.webdemo.common.vo.BaseVo;
import com.example.webdemo.common.vo.PageVo;
import com.example.webdemo.vo.request.UserRequest;

public interface UserService {
    /**
     * 分页查询用户及关联角色
     * @param req
     * @return
     */
    PageVo listUserRolesByParam(UserRequest req);

    /**
     * 查询所有
     * @return
     */
    List<User> queryAll();

    /**
     * 单个查询用户及用户菜单权限
     * @param u
     * @return
     */
    User getMenuByUid(User u);

    /**
     * 新增
     * @param request
     * @return
     */
    BaseVo save(User request);

    /**
     * 删除
     * @param req
     * @return
     */
    BaseVo remove(List<Integer> ids);

    /**
     * 修改
     * @param u
     * @return
     */
    BaseVo update(User u);
}
