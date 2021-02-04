package com.example.webdemo.activity.interceptor;

import java.util.Stack;

/**
 * @author tangaq
 * @date 2021/1/27
 */
public class Context {
    private static ThreadLocal<Stack<CommandContext>> commandContextThreadLocal = new ThreadLocal<>();

    public static CommandContext getCommandContext() {
        Stack<CommandContext> stack = getStack(commandContextThreadLocal);
        if (stack.isEmpty()) {
            return null;
        }
        return stack.peek();
    }

    private static Stack<CommandContext> getStack(ThreadLocal<Stack<CommandContext>> contextThreadLocal) {
        Stack<CommandContext> stack = contextThreadLocal.get();
        if (stack == null) {
            stack = new Stack<>();
            contextThreadLocal.set(stack);
        }
        return stack;
    }
}
