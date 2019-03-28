package com.example.webdemo.dao;

import com.example.webdemo.beans.User;
import com.example.webdemo.beans.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

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
     * @param param
     * @return
     */
    List<User> selectByParam(User param);

    /**
     * 翻页统计
     * @param param
     * @return
     */
    long countByParam(User param);
    
}