package com.example.webdemo.activity.interceptor;

/**
 * @author tangaq
 * @date 2021/1/27
 */
public abstract class AbstractCommandInterceptor implements CommandInterceptor {
    protected CommandInterceptor next;

    @Override
    public <T> T execute(CommandConfig config, Command<T> command) {
        return next.execute(config, command);
    }

    @Override
    public CommandInterceptor getNext() {
        return next;
    }

    @Override
    public void setNext(CommandInterceptor next) {
        this.next = next;
    }
}
