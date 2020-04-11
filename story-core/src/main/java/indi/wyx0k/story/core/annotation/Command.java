package indi.wyx0k.story.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * story
 * --
 * 被该注解注解了的函数将会被命令所调用,命令名是value值
 * @author wyx
 * --
 * 2020/4/11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    /**
     * 命令名称
     * @return
     */
    String value();

    /**
     * 当前注解的函数在命令中的调用顺序
     * @return
     */
    int order()default 0;
}
