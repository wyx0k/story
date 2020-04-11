package indi.wyx0k.story.core.common.impl;

import indi.wyx0k.story.core.common.StoryContext;
import indi.wyx0k.story.core.common.StoryEvent;
import indi.wyx0k.story.core.common.StoryEventPublisher;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
public class SimpleStoryEventPulisher implements StoryEventPublisher {
    @Override
    public StoryContext publish(StoryEvent storyEvent) {
        return null;
    }

    @Override
    public Boolean support(String name) {
        return true;
    }
}
