package com.college.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
public class SpringbootJpaMysqlDemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaMysqlDemoApplication.class, args);
		log.info("Spring boot JPA MYSQL Demo application started ......");
	}

}
