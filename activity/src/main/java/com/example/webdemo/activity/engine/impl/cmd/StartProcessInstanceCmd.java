package com.example.webdemo.activity.engine.impl.cmd;

import com.example.webdemo.activity.engine.runtime.ProcessInstance;
import com.example.webdemo.activity.interceptor.Command;
import com.example.webdemo.activity.interceptor.CommandContext;

import java.io.Serializable;

/**
 * @author tangaq
 * @date 2021/1/27
 */
public class StartProcessInstanceCmd<T> implements Command<ProcessInstance>, Serializable {
    @Override
    public ProcessInstance execute(CommandContext commandContext) {
        System.out.println("StartProcessInstanceCmd");
        return null;
    }
}
