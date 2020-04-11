package indi.wyx0k.story.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * story
 * --
 * 被该注解注解了的函数执行完后将触发一个事件,事件名是value值
 * @author wyx
 * --
 * 2020/4/11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReturnEvent {
    /**
     * 事件名称
     * @return
     */
    String value();
}
