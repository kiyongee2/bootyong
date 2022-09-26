package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BootWeb2Application extends SpringBootServletInitializer{

	//war로 배포시 상속 처리와 매소드 재정의 추가 부분
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BootWeb2Application.class);
	}

	//앱 실행
	public static void main(String[] args) {
		SpringApplication.run(BootWeb2Application.class, args);
	}
}
