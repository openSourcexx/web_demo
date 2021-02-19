package com.example.webdemo.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.webdemo.beans.ImportInfo;
import com.example.webdemo.mybatis.common.CommonMapper;

@Mapper
public interface ImportInfoMapper extends CommonMapper<ImportInfo> {
}