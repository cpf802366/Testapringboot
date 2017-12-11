package com.cpf.boottest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableScheduling
@SpringBootApplication
@Configuration
@ComponentScan(basePackages="com.cpf.boottest")  //默认扫描是当前包下的路径
@EnableAutoConfiguration
public class TestapringbootApplication  /*extends SpringBootServletInitializer*/ {
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(TestapringbootApplication.class);
//	}

	 public static void main(String[] args) {
		SpringApplication.run(TestapringbootApplication.class, args);
	}
}
