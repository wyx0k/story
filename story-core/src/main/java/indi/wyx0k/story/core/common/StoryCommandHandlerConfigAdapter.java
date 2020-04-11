package indi.wyx0k.story.core.common;

import indi.wyx0k.story.core.common.impl.SimpleAbstractStoryCommandHandler;

import java.util.List;
import java.util.Map;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/12
 */
public interface StoryCommandHandlerConfigAdapter {
    /**
     * 定义了获取storyCommandHandler的方法,这个就是给各个微服务提供的增加命令处理器的接口
     * @return
     */
    abstract Map<String,StoryCommandHandler> storyCommandHandlers();
}
