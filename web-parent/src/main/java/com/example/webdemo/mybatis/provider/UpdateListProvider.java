package com.example.webdemo.mybatis.provider;

import org.apache.ibatis.mapping.MappedStatement;

import com.example.webdemo.mybatis.common.SqlHelper;

import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;

/**
 * @author tangaq
 * @since 2020/1/10 14:00
 */
public class UpdateListProvider extends MapperTemplate {
    public UpdateListProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 批量更新，null值会被更新
     *
     * @param ms MappedStatement
     * @return sql语句
     */
    public String updateByPrimaryKeyList(MappedStatement ms) {
        return updateList(ms, false, false);
    }

    /**
     * 批量根据主键更新属性不为null的值
     *
     * @param ms MappedStatement
     * @return sql语句
     */
    public String updateByPrimaryKeySelectiveList(MappedStatement ms) {
        return updateList(ms, false, isNotEmpty());
    }

    /**
     * 批量更新
     *
     * @param ms MappedStatement
     * @param notNull 是否可为null
     * @param notEmpty 是否可为空
     * @return sql语句
     */
    private String updateList(MappedStatement ms, boolean notNull, boolean notEmpty) {
        return mysqlUpdateList(ms, notNull, notEmpty);
    }

    private String mysqlUpdateList(MappedStatement ms, boolean notNull, boolean notEmpty) {
        StringBuilder sql = new StringBuilder();
        Class<?> entityClass = getEntityClass(ms);
        String item = "record";
        sql.append("<foreach collection=\"list\" item=\"");
        sql.append(item);
        sql.append("\" separator=\";\">");
        sql.append(SqlHelper.updateTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.updateSetColumnsForList(entityClass, item, notNull, notEmpty));
        sql.append(SqlHelper.wherePKColumns(entityClass, item, true));
        sql.append("</foreach>");
        return sql.toString();
    }
}
