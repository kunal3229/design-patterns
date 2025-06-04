package com.kunal.design_pattern_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DesignPatternLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesignPatternLabApplication.class, args);
	}

}
