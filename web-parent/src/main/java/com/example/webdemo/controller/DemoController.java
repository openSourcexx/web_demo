package com.example.webdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo
 *
 * @author admin
 * @since 2019/9/25 16:16
 */
@RestController
@RequestMapping("/api")
public class DemoController {
    @RequestMapping("/demo")
    public void demo() {

    }
}
