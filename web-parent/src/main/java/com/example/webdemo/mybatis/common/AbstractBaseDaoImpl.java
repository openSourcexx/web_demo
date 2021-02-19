package com.example.webdemo.mybatis.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

public abstract class AbstractBaseDaoImpl<T, D extends CommonMapper<T>> implements IBaseDao<T> {
    @Autowired
    private D mapper;

    @Override
    public int insert(T record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return mapper.insertSelective(record);
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
    public int updateByPrimaryKey(T record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeySelectiveList(List<T> recordList) {
        return mapper.updateByPrimaryKeySelectiveList(recordList);
    }

    @Override
    public int updateByPrimaryKeyList(List<T> recordList) {
        return mapper.updateByPrimaryKeyList(recordList);
    }

    public D getMapper() {
        return mapper;
    }
}
