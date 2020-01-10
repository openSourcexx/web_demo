package com.example.webdemo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.webdemo.beans.User;
import com.example.webdemo.mybatis.common.CommonMapper;

@Mapper
public interface UserMapper extends CommonMapper<User> {
    /**
     * 单个查询
     * @param u
     * @return
     */
    User selectByUser(User u);

    /**
     * 查询所有用户
     * @return
     */
    List<User> listAll();

    /**
     * 条件查询
     * @param record
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<User> selectUserRolesByParam(@Param("record") User record,@Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    /**
     * 翻页统计
     * @param record
     * @param pageNo
     * @param pageSize
     * @return
     */
    long countUserRolesByParam(@Param("record") User record,@Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

}