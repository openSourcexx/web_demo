package com.example.webdemo.dao.mapper;

import com.example.webdemo.beans.DemoDo;
import com.example.webdemo.mybatis.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DemoMapper extends CommonMapper<DemoDo> {
    List<DemoDo> queryBatch(@Param("startNo") int startNo, @Param("size") int size);
}