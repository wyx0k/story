package indi.wyx0k.story.service.image;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StoryServiceImageApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoryServiceImageApplication.class, args);
	}

}
