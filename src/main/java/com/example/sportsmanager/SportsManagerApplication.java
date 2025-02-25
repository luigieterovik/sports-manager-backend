package com.example.sportsmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SportsManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsManagerApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/users/**")
						.allowedOrigins("https://sports-manager-frontend-tx6n.vercel.app")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*")
						.allowCredentials(true);

				registry.addMapping("/reservation/**")
						.allowedOrigins("https://sports-manager-frontend-tx6n.vercel.app")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*")
						.allowCredentials(true);

				registry.addMapping("/prices/**")
						.allowedOrigins("https://sports-manager-frontend-tx6n.vercel.app")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}

	
}
