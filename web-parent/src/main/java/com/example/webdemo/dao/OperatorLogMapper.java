package com.example.webdemo.dao;

import com.example.webdemo.beans.OperatorLog;
import com.example.webdemo.beans.example.OperatorLogExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperatorLogMapper {
    int countByExample(OperatorLogExample example);

    int deleteByExample(OperatorLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OperatorLog record);

    int insertSelective(OperatorLog record);

    List<OperatorLog> selectByExample(OperatorLogExample example);

    OperatorLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OperatorLog record, @Param("example") OperatorLogExample example);

    int updateByExample(@Param("record") OperatorLog record, @Param("example") OperatorLogExample example);

    int updateByPrimaryKeySelective(OperatorLog record);

    int updateByPrimaryKey(OperatorLog record);

    List<OperatorLog> listBySelective(@Param("record") OperatorLog record, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    long countBySelective(OperatorLog log);
}