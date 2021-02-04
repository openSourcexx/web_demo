package com.example.webdemo.activity.interceptor;

/**
 * @author tangaq
 * @date 2021/1/27
 */
public interface Command<T> {
    <T> T execute(CommandContext commandContext);
}
