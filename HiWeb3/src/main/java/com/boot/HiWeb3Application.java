package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class HiWeb3Application {

	public static void main(String[] args) {
		SpringApplication.run(HiWeb3Application.class, args);
	}

}
