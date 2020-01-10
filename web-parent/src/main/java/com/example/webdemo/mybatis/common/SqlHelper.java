package com.example.webdemo.mybatis.common;

import java.util.Set;

import tk.mybatis.mapper.LogicDeleteException;
import tk.mybatis.mapper.annotation.LogicDelete;
import tk.mybatis.mapper.annotation.Version;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.version.VersionException;

/**
 * @author tangaq
 * @since 2020/1/10 14:17
 */
public class SqlHelper extends tk.mybatis.mapper.mapperhelper.SqlHelper {
    /**
     * update list set列
     *
     * @param entityClass 对象实体
     * @param notNull 是否判断!=null
     * @param notEmpty 是否判断String类型!=''
     * @return set sql
     */
    public static String updateSetColumnsForList(Class<?> entityClass, String entityName, boolean notNull,
        boolean notEmpty) {
        StringBuilder sql = new StringBuilder();
        sql.append("<set>");
        //获取全部列
        Set<EntityColumn> columnSet = EntityHelper.getColumns(entityClass);
        //对乐观锁的支持
        EntityColumn versionColumn = null;
        // 逻辑删除列 @LogicDelete(notDeleteValue = "f", deleteValue = "t")
        EntityColumn logicDeleteColumn = null;
        //当某个列有主键策略时，不需要考虑他的属性是否为空，因为如果为空，一定会根据主键策略给他生成一个值
        for (EntityColumn column : columnSet) {
            if (column.getEntityField()
                .isAnnotationPresent(Version.class)) {
                if (versionColumn != null) {
                    throw new VersionException(
                        entityClass.getCanonicalName() + " 中包含多个带有 @Version 注解的字段，一个类中只能存在一个带有 @Version 注解的字段!");
                }
                versionColumn = column;
            }
            if (column.getEntityField()
                .isAnnotationPresent(LogicDelete.class)) {
                if (logicDeleteColumn != null) {
                    throw new LogicDeleteException(entityClass.getCanonicalName()
                        + " 中包含多个带有 @LogicDelete 注解的字段，一个类中只能存在一个带有 @LogicDelete 注解的字段!");
                }
                logicDeleteColumn = column;
            }
            if (!column.isId() && column.isUpdatable()) {
                if (column == versionColumn) {
                    Version version = versionColumn.getEntityField()
                        .getAnnotation(Version.class);
                    String versionClass = version.nextVersion()
                        .getCanonicalName();
                    sql.append(column.getColumn())
                        .append(" = ${@tk.mybatis.mapper.version.VersionUtil@nextVersion(")
                        .append("@")
                        .append(versionClass)
                        .append("@class, ")
                        .append("#{")
                        .append(entityName)
                        .append(".")
                        // ?字段名?
                        .append(column.getProperty())
                        .append("})}");
                } else if (column == logicDeleteColumn) {
                    sql.append(logicDeleteColumnEqualsValue(column, false))
                        .append(",");
                } else if (notNull) {
                    sql.append(
                        getIfNotNull(entityName, column, column.getColumnEqualsHolder(entityName) + ",", notEmpty));
                } else {
                    sql.append(column.getColumnEqualsHolder(entityName) + ",");
                }
            } else if (column.isId() && column.isUpdatable()) {
                // set id = id,
                sql.append(column.getColumn())
                    .append("=")
                    .append(column.getColumn())
                    .append(",");
            }
        }
        sql.append("</set>");
        return sql.toString();
    }

    /**
     * where主键条件
     *
     * @param entityClass 实体
     * @param entityName 前缀
     * @param useVersion 是否乐观锁
     * @return
     */
    public static String wherePKColumnsWithPrefix(Class<?> entityClass, String entityName, boolean useVersion) {
        StringBuilder sql = new StringBuilder();
        boolean hasLogicDelete = hasLogicDeleteColumn(entityClass);

        sql.append("<where>");
        //获取全部列
        Set<EntityColumn> columnSet = EntityHelper.getPKColumns(entityClass);
        //当某个列有主键策略时，不需要考虑他的属性是否为空，因为如果为空，一定会根据主键策略给他生成一个值
        for (EntityColumn column : columnSet) {
            sql.append(" AND ")
                .append(column.getColumnEqualsHolder(entityName));
        }
        if (useVersion) {
            sql.append(whereVersion(entityClass));
        }

        if (hasLogicDelete) {
            sql.append(whereLogicDelete(entityClass, false));
        }

        sql.append("</where>");
        return sql.toString();
    }
}
