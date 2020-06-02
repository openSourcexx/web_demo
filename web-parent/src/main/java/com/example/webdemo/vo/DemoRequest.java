package com.example.webdemo.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * @author admin
 * @version V2.1
 * @since 2.1.0 2019/5/11 20:59
 */
@Data
public class DemoRequest implements Serializable {
    private static final long serialVersionUID = -4292922266454919750L;
    private String id;

    private String name;

    private String key;

    private String channel;
}
