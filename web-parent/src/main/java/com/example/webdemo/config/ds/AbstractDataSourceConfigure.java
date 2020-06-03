package com.example.webdemo.config.ds;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * 多数据库
 *
 * @author admin
 * @since 2.1.0 2020/6/2 19:50
 */
public abstract class AbstractDataSourceConfigure {
    SqlSessionFactoryBean initSqlSessionFactoryBean(DataSource dataSource, String[] mapperLocation) throws IOException {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        Configuration configuration = new Configuration();
        sessionFactory.setConfiguration(configuration);
        sessionFactory.setDataSource(dataSource);
        Set<Resource> result = new HashSet(16);
        for (String location : mapperLocation) {
            result.addAll(Arrays.asList(new PathMatchingResourcePatternResolver().getResources(location)));
        }
        sessionFactory.setMapperLocations(result.toArray(new Resource[0]));
        return sessionFactory;
    }
}
