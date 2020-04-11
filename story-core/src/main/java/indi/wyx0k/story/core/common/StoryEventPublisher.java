package indi.wyx0k.story.core.common;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
public interface StoryEventPublisher {
    /**
     * 发布事件
     * @param storyEvent
     * @return
     */
    StoryContext publish(StoryEvent storyEvent);

    /**
     * 是否支持当前的事件名
     * @param name
     * @return
     */
    Boolean support(String name);
}
