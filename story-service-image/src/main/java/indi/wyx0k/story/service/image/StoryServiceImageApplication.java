package indi.wyx0k.story.service.image;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"indi.wyx0k.story.core.config","indi.wyx0k.story.service.image"})
public class StoryServiceImageApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoryServiceImageApplication.class, args);
	}

}
