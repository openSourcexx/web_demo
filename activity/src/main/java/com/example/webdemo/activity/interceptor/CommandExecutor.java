package com.example.webdemo.activity.interceptor;

/**
 * @author tangaq
 * @date 2021/1/27
 */
public interface CommandExecutor {
    CommandConfig getDefaultConfig();

    <T> T execute(CommandConfig commandConfig, Command<T> command);

    <T> T execute(Command<T> command);
}
