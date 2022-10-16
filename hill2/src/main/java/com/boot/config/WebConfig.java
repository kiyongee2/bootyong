package com.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	//404 에러 (정적 리소스- css, image, js 경로 설정해 줌)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/css/**")
		        .addResourceLocations("classpath:/static/css/");
		
		registry.addResourceHandler("/images/**")
				.addResourceLocations("classpath:/static/images/");
	}
}
