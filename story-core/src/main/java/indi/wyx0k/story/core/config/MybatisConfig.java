package indi.wyx0k.story.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/3/30
 */
@Configuration
@MapperScan("indi.wyx0k.story.core.mapper")
public class MybatisConfig {
}
