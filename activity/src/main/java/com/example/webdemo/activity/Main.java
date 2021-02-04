package com.example.webdemo.activity;

import com.example.webdemo.activity.engine.impl.cmd.StartProcessInstanceCmd;
import com.example.webdemo.activity.engine.runtime.ProcessInstance;
import com.example.webdemo.activity.interceptor.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangaq
 * @date 2021/1/27
 */
public class Main {
    public static void main(String[] args) {
        List<CommandInterceptor> chain = new ArrayList<>();
        chain.add(new LogInterceptor());
        chain.add(new CommandInvoker());
        for (int i = 0; i < chain.size() - 1; i++) {
            chain.get(i).setNext(chain.get(i + 1));
        }
        CommandInterceptor first = chain.get(0);
        CommandExecutor commandExecutor = new CommandExecutorImpl(null, first);

        commandExecutor.execute(new StartProcessInstanceCmd<ProcessInstance>());
    }
}
