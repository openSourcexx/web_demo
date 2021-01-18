package com.example.webdemo.mybatis.record;

import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * @author admin
 * @date 2021/1/16
 */
@RegisterMapper
public interface UpdateRecordMapper<T> extends UpdateByRecordMapper<T>, UpdateByRecordSelectiveMapper<T> {
}
