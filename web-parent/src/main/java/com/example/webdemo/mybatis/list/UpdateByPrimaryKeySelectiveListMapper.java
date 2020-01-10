package com.example.webdemo.mybatis.list;

import java.util.List;

import org.apache.ibatis.annotations.UpdateProvider;

import com.example.webdemo.mybatis.provider.UpdateListProvider;

import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 针对于mysql，要求配置url时带上allowMultiQueries=true
 *
 * @author tangaq
 * @since 2020/1/10 13:58
 */
@RegisterMapper
public interface UpdateByPrimaryKeySelectiveListMapper<T> {
    /**
     * 批量更新，null值不更新
     *
     * @param recordList 记录
     * @return
     */
    @UpdateProvider(type = UpdateListProvider.class, method = "dynamicSQL")
    int updateByPrimaryKeySelectiveList(List<T> recordList);
}
