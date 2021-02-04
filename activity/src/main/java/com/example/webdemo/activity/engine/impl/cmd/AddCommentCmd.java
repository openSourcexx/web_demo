package com.example.webdemo.activity.engine.impl.cmd;

import com.example.webdemo.activity.interceptor.Command;
import com.example.webdemo.activity.interceptor.CommandContext;
import com.example.webdemo.activity.task.Comment;

/**
 * @author tangaq
 * @date 2021/1/27
 */
public class AddCommentCmd implements Command<Comment> {
    @Override
    public Comment execute(CommandContext commandContext) {
        System.out.println("AddCommentCmd");
        return null;
    }
}
