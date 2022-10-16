package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Hiweb2Application {

	public static void main(String[] args) {
		SpringApplication.run(Hiweb2Application.class, args);
	}

}
