package indi.wyx0k.story.core.common;

import indi.wyx0k.story.core.exeception.CommandHandleException;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
public interface StoryCommandHandler {
    /**
     * 处理命令
     * @param storyContext
     * @return
     */
    StoryContext handleCommand(StoryContext storyContext) throws CommandHandleException;

    /**
     * 判断命令是否支持
     * @param name
     * @return
     */
    Boolean support(String name);

}
