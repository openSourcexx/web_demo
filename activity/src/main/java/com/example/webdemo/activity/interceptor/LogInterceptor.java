package com.example.webdemo.activity.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tangaq
 * @date 2021/1/27
 */
public class LogInterceptor extends AbstractCommandInterceptor {
    private static Logger log = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public <T> T execute(CommandConfig commandConfig, Command<T> command) {
        log.info("LogInterceptor");
        return next.execute(commandConfig, command);
    }
}
