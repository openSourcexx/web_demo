package com.example.webdemo.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author tangaq
 * @date 2020/12/23
 */
public class ThrowableUtil {
    /**
     * parse error to string
     *
     * @param e
     * @return
     */
    public static String toString(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        String errorMsg = stringWriter.toString();
        return errorMsg;
    }

}
