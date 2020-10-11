package com.example.webdemo.dao;

import com.example.webdemo.beans.RolePermissionRelation;
import com.example.webdemo.beans.example.UserRoleRelationExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolePermissionRelationMapper {
    int countByExample(UserRoleRelationExample.RolePermissionRelationExample example);

    int deleteByExample(UserRoleRelationExample.RolePermissionRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RolePermissionRelation record);

    int insertSelective(RolePermissionRelation record);

    List<RolePermissionRelation> selectByExample(UserRoleRelationExample.RolePermissionRelationExample example);

    RolePermissionRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RolePermissionRelation record, @Param("example") UserRoleRelationExample.RolePermissionRelationExample example);

    int updateByExample(@Param("record") RolePermissionRelation record, @Param("example") UserRoleRelationExample.RolePermissionRelationExample example);

    int updateByPrimaryKeySelective(RolePermissionRelation record);

    int updateByPrimaryKey(RolePermissionRelation record);
}