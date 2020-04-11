package indi.wyx0k.story.core.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * story
 * --
 * 在这向协同引擎发送注册的事件和命令
 * @author wyx
 * --
 * 2020/4/10
 */
@Configuration
@Data
public class StoryEventCommandConfig {
    @Value("${story.engine.location}")
    String engineLocation;
}
