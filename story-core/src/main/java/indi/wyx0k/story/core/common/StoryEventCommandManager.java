package indi.wyx0k.story.core.common;

import java.util.List;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
public interface StoryEventCommandManager {
    /**
     * 列举当前微服务支持的所有命令的定义bean
     * @return
     */
    List<StoryCommand> listCommand();

    /**
     * 根据名称获取命令的定义bean
     * @param name
     * @return
     */
    StoryCommand getCommand(String name);

    /**
     * 判断是否支持该命令
     * @param name
     * @return
     */
    Boolean surportedCommand(String name);

    /**
     * 根据名称获取对应的命令处理bean
     * @param commandName
     * @return
     */
    StoryCommandHandler getCommandHandler(String commandName);

    /**
     * 处理命令
     * @param commandName
     * @param storyContext
     * @return
     */
    StoryContext handleCommand(String commandName,StoryContext storyContext);

    /**
     * 指定一个命令处理bean去处理命令
     * @param storyContext
     * @param storyCommandHandler
     * @return
     */
    StoryContext handleCommand(StoryContext storyContext,StoryCommandHandler storyCommandHandler);

    /**
     * 推送事件
     * @param storyEvent
     * @param storyContext
     */
    void publishEvent(StoryEvent storyEvent,StoryContext storyContext);
}
