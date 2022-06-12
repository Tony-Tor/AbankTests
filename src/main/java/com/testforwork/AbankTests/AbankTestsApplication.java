package com.testforwork.AbankTests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class AbankTestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbankTestsApplication.class, args);
	}

	@Bean
	public Logger logger(){
		return LoggerFactory.getLogger("logger");
	}
}
