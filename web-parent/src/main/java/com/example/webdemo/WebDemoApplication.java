package com.example.webdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.example.webdemo.**"})
// 这里扫描路径要和xml包路径必须一致才能对应
// @MapperScan("com.example.webdemo.dao.**")
@MapperScan("com.example.webdemo.dao.mapper.**")
public class WebDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebDemoApplication.class, args);
	}

}
