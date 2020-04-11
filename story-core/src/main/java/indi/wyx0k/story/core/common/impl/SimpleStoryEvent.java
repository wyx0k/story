package indi.wyx0k.story.core.common.impl;

import indi.wyx0k.story.core.common.StoryContext;
import indi.wyx0k.story.core.common.StoryEvent;
import indi.wyx0k.story.core.constant.EventType;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
public class SimpleStoryEvent implements StoryEvent {
    private String uuid;
    private String eventName;
    private String serviceName;
    private StoryContext storyContext;
    private EventType eventType;
    private String processId;

    @Override
    public String getEventName() {
        return eventName;
    }

    @Override
    public void setEventName(String eventName) {
        this.eventName = eventName;
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
    public EventType getEventType() {
        return eventType;
    }

    @Override
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public String getProcessId() {
        return processId;
    }

    @Override
    public void setProcessId(String processId) {
        this.processId = processId;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
