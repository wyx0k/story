package indi.wyx0k.story.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({"indi.wyx0k.story.core.config","indi.wyx0k.story.service.user"})
public class StoryServiceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoryServiceUserApplication.class, args);
	}

}
