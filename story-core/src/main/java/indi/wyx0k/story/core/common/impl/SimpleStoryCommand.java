package indi.wyx0k.story.core.common.impl;

import indi.wyx0k.story.core.common.StoryCommand;
import indi.wyx0k.story.core.common.StoryContext;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
public class SimpleStoryCommand implements StoryCommand {
    private String uuid;
    private String commandName;
    private String serviceName;
    private StoryContext storyContext;
    private String processId;

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public StoryContext getStoryContext() {
        return storyContext;
    }

    @Override
    public void setStoryContext(StoryContext storyContext) {
        this.storyContext = storyContext;
    }

    @Override
    public String getProcessId() {
        return processId;
    }

    @Override
    public void setProcessId(String processId) {
        this.processId = processId;
    }
}
