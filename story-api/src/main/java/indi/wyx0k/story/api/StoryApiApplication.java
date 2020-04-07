package indi.wyx0k.story.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableDiscoveryClient
@EnableWebSecurity
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan("indi.wyx0k.story")
public class StoryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoryApiApplication.class, args);
    }

}
