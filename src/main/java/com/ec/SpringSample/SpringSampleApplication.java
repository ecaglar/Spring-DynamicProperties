package com.ec.SpringSample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSampleApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringSampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println(PropertyUtil.getProperty("file.path"));
			System.out.println(PropertyUtil.getProperty("common.path"));
		};
	}
}
