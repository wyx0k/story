package indi.wyx0k.story.core.common.impl;

import indi.wyx0k.story.core.common.StoryCommandHandler;
import indi.wyx0k.story.core.common.StoryContext;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
public class SimpleStoryCommandHandler implements StoryCommandHandler {
    @Override
    public StoryContext handleCommand(StoryContext storyContext) {
        return null;
    }

    @Override
    public Boolean support(String name) {
        return true;
    }
}
