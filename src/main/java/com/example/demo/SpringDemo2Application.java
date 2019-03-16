package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.example.demo.controller", "com.example.demo.service", "com.example.demo.dao",
		"com.example.demo.domain" })
// @SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
// 由于这个exclude出现了错误,因为没有加载DataSourceAutoConfiguration配置出现了JdbcTemplate加载不到
@SpringBootApplication
public class SpringDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemo2Application.class, args);
	}

}
