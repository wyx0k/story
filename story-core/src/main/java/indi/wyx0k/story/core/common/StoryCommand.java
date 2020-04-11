package indi.wyx0k.story.core.common;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
public interface StoryCommand {
    /**
     * 命令的名称
     * @return
     */
    String getCommandName();
    void setCommandName(String commandName);

    /**
     * 命令所属服务的名称
     * @return
     */
    String getServiceName();
    void setServiceName(String serviceName);
    /**
     * story的环境包含了处理命令所需要的数据
     * @return
     */
    StoryContext getStoryContext();
    void setStoryContext(StoryContext storyContext);

    /**
     * 流程id
     * @return
     */
    String getProcessId();
    void setProcessId(String processId);

    /**
     * uuid
     * @return
     */
    String getUuid();
    void setUuid(String uuid);
}
