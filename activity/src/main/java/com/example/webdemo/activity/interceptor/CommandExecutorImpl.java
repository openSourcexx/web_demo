package com.example.webdemo.activity.interceptor;

/**
 * @author tangaq
 * @date 2021/1/27
 */
public class CommandExecutorImpl implements CommandExecutor {
    private CommandConfig defaultConfig;

    private CommandInterceptor first;

    public CommandExecutorImpl(CommandConfig defaultConfig, CommandInterceptor first) {
        this.defaultConfig = defaultConfig;
        this.first = first;
    }

    @Override
    public CommandConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public <T> T execute(Command<T> command) {
        return execute(defaultConfig, command);
    }

    @Override
    public <T> T execute(CommandConfig commandConfig, Command<T> command) {
        return first.execute(commandConfig, command);
    }
}
