package indi.wyx0k.story.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class StoryServiceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoryServiceUserApplication.class, args);
	}

}
