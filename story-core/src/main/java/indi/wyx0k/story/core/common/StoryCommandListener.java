package indi.wyx0k.story.core.common;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
public interface StoryCommandListener {
    /**
     * 监听的mq类型
     * @return
     */
    String getType();

    /**
     * 监听
     */
    void listen();

    /**
     * 获取事件命令管理器
     * @return
     */
    StoryEventCommandManager getStoryEventCommandManager();

}
