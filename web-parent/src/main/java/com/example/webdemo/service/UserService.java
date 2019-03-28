package com.example.webdemo.service;

import com.example.webdemo.beans.User;
import com.example.webdemo.common.vo.BaseVo;
import com.example.webdemo.common.vo.PageVo;
import com.example.webdemo.vo.request.UserRequest;

import java.util.List;

public interface UserService {
    /**
     * 翻页查询
     * @param req
     * @return
     */
    PageVo query(UserRequest req);

    /**
     * 查询所有
     * @return
     */
    List<User> queryAll();

    /**
     * 单个查询
     * @param u
     * @return
     */
    User getById(User u);

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
