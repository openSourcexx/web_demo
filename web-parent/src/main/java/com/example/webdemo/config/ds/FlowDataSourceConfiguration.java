package com.example.webdemo.config.ds;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * flow数据库
 *
 * @author admin
 * @since 2.1.0 2020/6/2 20:04
 */
@Configuration
@MapperScan(basePackages = {"com.hsjry.**.flow.dao.mapper.**", "com.example.webdemo.dao.mapper.flow"},
    sqlSessionFactoryRef = "flowSqlSessionFactory")
public class FlowDataSourceConfiguration extends AbstractDataSourceConfigure {
    /** 精确到 flow 目录，以便跟其他数据源隔离 */
    private static final String[] MAPPER_LOCATION = {"classpath*:com/example/webdemo/dao/mapper/flow/*.xml"};

    @Value("${flow.datasource.url}")
    private String url;

    @Value("${flow.datasource.username}")
    private String user;

    @Value("${flow.datasource.password}")
    private String password;

    @Value("${datasource.driverClassName}")
    private String driverClass;

    @Autowired(required = false)
    private DatabaseIdProvider databaseIdProvider;

    @Bean(name = "flowDataSource")
    public DataSource flowDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "flowTransactionManager")
    public DataSourceTransactionManager flowTransactionManager() {
        return new DataSourceTransactionManager(flowDataSource());
    }

    @Bean(name = "flowSqlSessionFactory")
    public SqlSessionFactory flowSqlSessionFactory(@Qualifier("flowDataSource") DataSource demoDataSource)
    throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = initSqlSessionFactoryBean(demoDataSource, MAPPER_LOCATION);
        sqlSessionFactoryBean.setDatabaseIdProvider(databaseIdProvider);
        return sqlSessionFactoryBean.getObject();
    }
}
