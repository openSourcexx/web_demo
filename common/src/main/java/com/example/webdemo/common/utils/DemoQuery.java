package com.example.webdemo.common.utils;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * todo
 *
 * @author aq
 * @since 2019/5/30 16:02
 */
@Data
@Builder
public class DemoQuery {
    private String name;

    private int age;
    private Date c;
}
