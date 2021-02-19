package com.example.webdemo.mybatis.common;

import java.util.List;

public interface IBaseDao<T> {

    /**
     * 全量插入数据
     *
     * @param record 数据
     * @return 数据
     */
    int insert(T record);

    /**
     * 插入非null数据
     *
     * @param record 数据
     * @return 数据
     */
    int insertSelective(T record);

    /**
     * 批量插入，如果record中的主键值为null，则会使用数据库中的自增；如果数据库中未设置自增，则record中必须塞值，否则报错
     *
     * @param recordList 数据
     * @return 插入数据量
     */
    int insertList(List<T> recordList);

    /**
     * 根据record删除数据
     *
     * @param record 数据
     * @return 影响数据条数
     */
    int deleteByRecord(T record);

    /**
     * 根据主键删除数据
     *
     * @param key 主键
     * @return 影响数据条数
     */
    int deleteByPrimaryKey(Object key);

    /**
     * 根据record中非null作为条件查询数据
     *
     * @param record 数据条件
     * @return 查询出来的数据
     */
    List<T> selectByRecord(T record);

    /**
     * 根据record中非null作为条件查询数据，仅查出至多一条，否则报错
     * 如果根据条件查询出来的数据会是多条的，则该接口会直接抛出异常，不会进行{@link List#get(int)}操作
     *
     * @param record 数据条件
     * @return 查询出来的数据
     */
    T selectOneByRecord(T record);

    /**
     * 根据主键查询数据
     *
     * @param key 主键
     * @return 数据
     */
    T selectByPrimaryKey(Object key);

    /**
     * 根据record中非null作为条件查询总数据量
     *
     * @param record 数据条件
     * @return 符合要求的数据量
     */
    int selectCountByRecord(T record);

    /**
     * 根据主键查询是否存在数据
     *
     * @param key 主键
     * @return true-存在数据 false-不存在数据
     */
    boolean existsWithPrimaryKey(Object key);

    /**
     * 根据record中的主键，全量修改数据 record必须带主键
     *
     * @param record 数据
     * @return 修改的数据
     */
    int updateByPrimaryKey(T record);

    /**
     * 根据record中的主键，非null字段被修改数据 record必须带主键
     *
     * @param record 数据
     * @return 修改的数据
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 批量根据主键更新属性不为null的值
     * 针对于mysql，要求配置url时带上allowMultiQueries=true
     *
     * @param recordList 记录
     */
    int updateByPrimaryKeySelectiveList(List<T> recordList);

    /**
     * 批量更新，null值会被更新
     * 针对于mysql，要求配置url时带上allowMultiQueries=true
     *
     * @param recordList 记录
     */
    int updateByPrimaryKeyList(List<T> recordList);
}
