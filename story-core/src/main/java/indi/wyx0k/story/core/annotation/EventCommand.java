package indi.wyx0k.story.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * story
 * --
 * 被该注解注解的函数会同时注册同名的事件和命令
 * @author wyx
 * --
 * 2020/4/11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventCommand {
    /**
     * 事件和命令的名称
     * @return
     */
    String value();
}
