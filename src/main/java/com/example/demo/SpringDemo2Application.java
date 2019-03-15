package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.example.demo.controller", "com.example.demo.service", "com.example.demo.dao",
		"com.example.demo.domain" })
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SpringDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemo2Application.class, args);
	}

}
