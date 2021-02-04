package com.example.webdemo.activity.interceptor;

/**
 * @author tangaq
 * @date 2021/1/27
 */
public class CommandInvoker extends AbstractCommandInterceptor {
    @Override
    public <T> T execute(CommandConfig config, Command<T> command) {
        return command.execute(Context.getCommandContext());
    }


    @Override
    public CommandInterceptor getNext() {
        return null;
    }

    @Override
    public void setNext(CommandInterceptor next) {
        return;
    }
}
