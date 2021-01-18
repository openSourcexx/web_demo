package com.example.webdemo.common.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * todo
 *
 * @author aq
 * @since 2019/5/30 16:02
 */
@Data
public class Demo implements Serializable {
    private static final long serialVersionUID = -4579543569644525415L;
    private String name;

    private int age;
    private Date c;
}
