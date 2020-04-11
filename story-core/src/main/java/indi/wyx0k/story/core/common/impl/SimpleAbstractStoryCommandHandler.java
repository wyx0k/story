package indi.wyx0k.story.core.common.impl;

import indi.wyx0k.story.core.common.StoryCommandHandler;
import indi.wyx0k.story.core.common.StoryContext;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/12
 */
@Data
public abstract class SimpleAbstractStoryCommandHandler implements StoryCommandHandler {
    @Override
    public Boolean support(String name) {
        return true;
    }
}
