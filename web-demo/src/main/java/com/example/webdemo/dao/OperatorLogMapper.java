package com.example.webdemo.dao;

import com.example.webdemo.beans.OperatorLog;
import com.example.webdemo.beans.OperatorLogExample;
import java.util.List;

import com.example.webdemo.vo.request.OperatorLogRequest;
import com.example.webdemo.vo.response.OperatorLogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OperatorLogMapper {
    int countByExample(OperatorLogExample example);

    int deleteByExample(OperatorLogExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(OperatorLog record);

    List<OperatorLog> selectByExample(OperatorLogExample example);

    OperatorLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OperatorLog record, @Param("example") OperatorLogExample example);

    int updateByPrimaryKeySelective(OperatorLog record);

    int updateByPrimaryKey(OperatorLog record);

    List<OperatorLog> listBySelective(@Param("record") OperatorLog record,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);

    long countBySelective(OperatorLog log);
}