package com.example.webdemo.service;

import com.example.webdemo.beans.User;
import com.example.webdemo.vo.PageVo;
import com.example.webdemo.vo.request.UserRequest;

import java.util.List;

public interface UserService {
    PageVo query(UserRequest req);

    List<User> queryAll();

    User getById(User u);
}
