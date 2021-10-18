package com.college.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

/*
 * if we're using a Spring Boot project and have a spring-data-* or spring-tx dependencies on the classpath, then 
 * transaction management will be enabled by default.
 * @EnableTransactionManagement
 */
@Slf4j
@SpringBootApplication
public class SpringbootJpaMysqlDemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaMysqlDemoApplication.class, args);
		log.info("Spring boot JPA MYSQL Demo application started ......");
	}

}
