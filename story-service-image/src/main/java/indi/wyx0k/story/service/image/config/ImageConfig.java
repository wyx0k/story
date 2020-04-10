package indi.wyx0k.story.service.image.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/10
 */
@Component
@ConfigurationProperties(prefix = "story.image")
@Data
public class ImageConfig {
    private String path;
}
