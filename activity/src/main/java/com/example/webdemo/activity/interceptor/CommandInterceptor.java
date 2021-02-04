package com.example.webdemo.activity.interceptor;


/**
 * @author tangaq
 * @date 2021/1/27
 */
public interface CommandInterceptor {
    <T> T execute(CommandConfig config, Command<T> command);

    CommandInterceptor getNext();

    void setNext(CommandInterceptor next);
}
