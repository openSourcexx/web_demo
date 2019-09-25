package com.example.webdemo.dao.mapper;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;

/**
 * 通用mapper
 *
 * @author admin
 * @since 2019/9/25 18:36
 */
@RegisterMapper
public interface CommonMapper<T> extends Mapper<T>, BaseMapper<T>, SelectByExampleMapper<T>, InsertListMapper<T> {
}
