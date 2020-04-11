package indi.wyx0k.story.core.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
@Data
@Component
@ConfigurationProperties(prefix = "story.engine")
public class StoryEngineConstant {
    private String location;
    private String mqType;
    @Value("${spring.application.name}")
    private String applicationName;
}
