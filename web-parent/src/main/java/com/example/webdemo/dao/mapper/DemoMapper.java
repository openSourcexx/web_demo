package com.example.webdemo.dao.mapper;

import com.example.webdemo.beans.DemoDo;
import com.example.webdemo.mybatis.common.CommonMapper;

public interface DemoMapper extends CommonMapper<DemoDo> {
    void query();
}