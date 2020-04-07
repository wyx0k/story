package indi.wyx0k.story.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class StoryCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoryCenterApplication.class, args);
	}

}
