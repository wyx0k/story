package indi.wyx0k.story.core.common;

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
    StoryContext handleCommand(StoryContext storyContext);

    /**
     * 判断命令是否支持
     * @param name
     * @return
     */
    Boolean support(String name);
}
