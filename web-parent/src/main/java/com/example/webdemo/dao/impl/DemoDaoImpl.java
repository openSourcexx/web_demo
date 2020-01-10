package com.example.webdemo.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.example.webdemo.beans.DemoDo;
import com.example.webdemo.dao.DemoDao;
import com.example.webdemo.dao.mapper.DemoMapper;
import com.example.webdemo.mybatis.common.AbstractBaseDaoImpl;

/**
 * @author tangaq
 * @since 2020/1/10 16:21
 */
@Repository
public class DemoDaoImpl extends AbstractBaseDaoImpl<DemoDo, DemoMapper> implements DemoDao {
    @Override
    public void insertListBySelective(List<DemoDo> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.stream()
                .forEach(insertDo -> {
                    insertDo.setCreateTime(new Date());
                    insertDo.setUpdateTime(new Date());
                });
            getMapper().insertList(list);
        }
    }
}
