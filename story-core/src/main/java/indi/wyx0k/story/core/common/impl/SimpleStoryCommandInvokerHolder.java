package indi.wyx0k.story.core.common.impl;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/13
 */
@Data
public class SimpleStoryCommandInvokerHolder {
    private Method method;
    private Object owner;
}
