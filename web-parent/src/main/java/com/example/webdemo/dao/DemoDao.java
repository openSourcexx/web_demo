package com.example.webdemo.dao;

import java.util.List;

import com.example.webdemo.beans.DemoDo;
import com.example.webdemo.mybatis.common.IBaseDao;

/**
 * @author tangaq
 * @since 2020/1/10 16:26
 */
public interface DemoDao extends IBaseDao<DemoDo> {
    /**
     * 批量新增
     *
     * @param list
     */
    void insertListBySelective(List<DemoDo> list);
}
