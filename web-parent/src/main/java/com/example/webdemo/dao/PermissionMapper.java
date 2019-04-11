package com.example.webdemo.dao;

import com.example.webdemo.beans.Permission;
import com.example.webdemo.beans.PermissionExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PermissionMapper {
    int countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    /**
     * 获取用户拥有权限
     * @param uid
     * @return
     */
    List<Permission> selectPermissionMenuByUid(Integer uid);

    List<Permission> selectAll();

    /**
     * 获取角色拥有权限
     * @param rid
     * @return
     */
    List<Permission> selectPermissionMenuByRid(Integer rid);

    Permission selectChildrenById(@Param("id") Integer id);
}