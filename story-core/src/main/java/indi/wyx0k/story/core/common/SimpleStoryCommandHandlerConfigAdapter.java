package indi.wyx0k.story.core.common;


import indi.wyx0k.story.core.common.impl.SimpleAbstractStoryCommandHandler;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/12
 */
public abstract class SimpleStoryCommandHandlerConfigAdapter implements StoryCommandHandlerConfigAdapter {
    /**
     * 定义了获取SimpleAbstractStoryCommandHandler的方法,这个就是给各个微服务提供的增加命令处理器的接口
     * @return
     */
    abstract Map<String,SimpleAbstractStoryCommandHandler> getSimpleAbstractStoryCommandHandlers();

    @Override
    public Map<String,StoryCommandHandler> storyCommandHandlers() {
        Map<String,SimpleAbstractStoryCommandHandler> map = getSimpleAbstractStoryCommandHandlers();
        return map.entrySet().stream().collect(Collectors.toMap(key->key.getKey(),value->((StoryCommandHandler)value.getValue())));
    }
}
