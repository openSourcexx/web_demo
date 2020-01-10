package com.example.webdemo.mybatis.list;

import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 批量更新通用mapper
 *
 * @author admin
 * @since 2020/1/10 14:52
 */
@RegisterMapper
public interface UpdateListMapper<T> extends UpdateByPrimaryKeyListMapper<T>, UpdateByPrimaryKeySelectiveListMapper<T> {
}
