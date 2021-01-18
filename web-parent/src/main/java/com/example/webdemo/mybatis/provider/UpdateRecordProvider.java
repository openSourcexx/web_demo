package com.example.webdemo.mybatis.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

/**
 * 通过对象修改
 *
 * @author admin
 * @date 2021/1/16
 */
public class UpdateRecordProvider extends MapperTemplate {
    public UpdateRecordProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String updateByRecord(MappedStatement ms) {
        return updateRecord(ms, false, false);
    }

    public String updateByRecordSelective(MappedStatement ms) {
        return updateRecord(ms, true, true);
    }

    /**
     * 通过对象修改数据
     *
     * @param ms               MappedStatement
     * @param updateIfNotNull  是否更新不为null数据 true:只修改不为null字段
     * @param updateIfNotEmpty 是否更新不为空数据 true:只修改不为空
     * @return
     */
    private String updateRecord(MappedStatement ms, boolean updateIfNotNull, boolean updateIfNotEmpty) {
        StringBuilder sql = new StringBuilder();
        Class<?> entityClass = getEntityClass(ms);
        sql.append(SqlHelper.updateTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.updateSetColumnsIgnoreVersion(entityClass, "record", updateIfNotNull,
                updateIfNotEmpty));
        sql.append(com.example.webdemo.mybatis.common.SqlHelper.whereUpdateColumns(entityClass,"example"));
        return sql.toString();
    }
}
