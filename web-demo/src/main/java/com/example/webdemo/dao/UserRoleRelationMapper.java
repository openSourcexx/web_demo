package com.example.webdemo.dao;

import com.example.webdemo.beans.UserRoleRelation;
import com.example.webdemo.beans.UserRoleRelationExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleRelationMapper {
    int countByExample(UserRoleRelationExample example);

    int deleteByExample(UserRoleRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleRelation record);

    int insertSelective(UserRoleRelation record);

    List<UserRoleRelation> selectByExample(UserRoleRelationExample example);

    UserRoleRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRoleRelation record, @Param("example") UserRoleRelationExample example);

    int updateByExample(@Param("record") UserRoleRelation record, @Param("example") UserRoleRelationExample example);

    int updateByPrimaryKeySelective(UserRoleRelation record);

    int updateByPrimaryKey(UserRoleRelation record);
}