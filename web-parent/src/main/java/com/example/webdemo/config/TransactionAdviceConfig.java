package com.example.webdemo.config;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

/**
 * @author tangaq
 * @date 2019/4/11
 */

@Configuration
public class TransactionAdviceConfig {
    private static final String TRANS_DEFAULT_LEVEL = "PROPAGATION_REQUIRED,-Exception";

    private static final String TRANS_SELECT_LEVEL = "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly";

    private DataSourceTransactionManager transactionManager;

    @Autowired
    private void setTransactionManager(DataSourceTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    // 创建事务通知
    @Bean(name = "txAdvice")
    public TransactionInterceptor getAdvisor() {
        Properties properties = new Properties();
        properties.setProperty("get*", TRANS_SELECT_LEVEL);
        properties.setProperty("list*", TRANS_SELECT_LEVEL);
        properties.setProperty("query*", TRANS_SELECT_LEVEL);
        properties.setProperty("select*", TRANS_SELECT_LEVEL);
        properties.setProperty("count*", TRANS_SELECT_LEVEL);
        properties.setProperty("add*", TRANS_DEFAULT_LEVEL);
        properties.setProperty("save*", TRANS_DEFAULT_LEVEL);
        properties.setProperty("insert*", TRANS_DEFAULT_LEVEL);
        properties.setProperty("update*", TRANS_DEFAULT_LEVEL);
        properties.setProperty("delete*", TRANS_DEFAULT_LEVEL);
        return new TransactionInterceptor(transactionManager,properties);
    }

    @Bean
    public BeanNameAutoProxyCreator txProxy() {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setInterceptorNames("txAdvice");
        creator.setBeanNames("*ServiceImpl");
        creator.setProxyTargetClass(true);
        return creator;
    }
}
