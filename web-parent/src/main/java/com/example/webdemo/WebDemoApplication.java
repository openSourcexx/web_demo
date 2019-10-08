package com.example.webdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// 扫描mapper
@MapperScan(basePackages = {"com.example.webdemo.dao.**"})
public class WebDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebDemoApplication.class, args);
	}

}
