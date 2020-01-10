package com.example.webdemo.mybatis.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

public abstract class AbstractBaseDaoImpl<T, D extends CommonMapper<T>> implements IBaseDao<T> {
    @Autowired
    private D mapper;

    @Override
    public T insert(T record) {
        mapper.insert(record);
        return record;
    }

    @Override
    public T insertSelective(T record) {
        mapper.insertSelective(record);
        return record;
    }

    @Override
    public int insertList(List<T> recordList) {
        if (CollectionUtils.isEmpty(recordList)) {
            return 0;
        }
        return mapper.insertList(recordList);
    }

    @Override
    public int deleteByRecord(T record) {
        return mapper.delete(record);
    }

    @Override
    public int deleteByPrimaryKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public List<T> selectByRecord(T record) {
        return mapper.select(record);
    }

    @Override
    public T selectOneByRecord(T record) {
        return mapper.selectOne(record);
    }

    @Override
    public T selectByPrimaryKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public int selectCountByRecord(T record) {
        return mapper.selectCount(record);
    }

    @Override
    public boolean existsWithPrimaryKey(Object key) {
        return mapper.existsWithPrimaryKey(key);
    }

    @Override
    public T updateByPrimaryKey(T record) {
        mapper.updateByPrimaryKey(record);
        return record;
    }

    @Override
    public T updateByPrimaryKeySelective(T record) {
        mapper.updateByPrimaryKeySelective(record);
        return record;
    }

    @Override
    public void updateByPrimaryKeySelectiveList(List<T> recordList) {
        mapper.updateByPrimaryKeySelectiveList(recordList);
    }

    @Override
    public void updateByPrimaryKeyList(List<T> recordList) {
        mapper.updateByPrimaryKeyList(recordList);
    }

    public D getMapper() {
        return mapper;
    }
}
