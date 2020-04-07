package indi.wyx0k.story.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class StoryEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoryEngineApplication.class, args);
	}

}
