package indi.wyx0k.story.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * story
 *
 * @Author wyx0k
 * 2019/11/10
 **/
@Configuration
public class CORSConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET","POST","DELETE","PUT")
                .allowedOrigins("*")
                .maxAge(3600)
                .allowCredentials(true)
                .allowedHeaders("*");
    }
}
