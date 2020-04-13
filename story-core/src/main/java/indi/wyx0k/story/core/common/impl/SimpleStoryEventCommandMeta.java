package indi.wyx0k.story.core.common.impl;

import indi.wyx0k.story.core.common.StoryEvent;
import indi.wyx0k.story.core.common.StoryEventCommandMeta;
import lombok.Data;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
public class SimpleStoryEventCommandMeta implements StoryEventCommandMeta {
    private String serviceName;
    private Collection<String>  eventNames;
    private Collection<String> commandNames;
    private Boolean registed;
    private String commandQueueName;
    private Long expireTime;
    private  Map<String, List<String>> ignoredEvent;

    @Override
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public Collection<String> getEventNames() {
        return eventNames;
    }

    public void setEventNames(Collection<String> eventNames) {
        this.eventNames = eventNames;
    }

    @Override
    public Collection<String> getCommandNames() {
        return commandNames;
    }

    public void setCommandNames(Collection<String> commandNames) {
        this.commandNames = commandNames;
    }

    @Override
    public Boolean getRegisted() {
        return registed;
    }

    public void setRegisted(Boolean registed) {
        this.registed = registed;
    }

    @Override
    public String getCommandQueueName() {
        return commandQueueName;
    }

    public void setCommandQueueName(String commandQueueName) {
        this.commandQueueName = commandQueueName;
    }

    @Override
    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public Map<String, List<String>> getIgnoredEvent() {
        return ignoredEvent;
    }

    public void setIgnoredEvent(Map<String, List<String>> ignoredEvent) {
        this.ignoredEvent = ignoredEvent;
    }

    @Override
    public String toString() {
        return "SimpleStoryEventCommandMeta{" +
                "serviceName='" + serviceName + '\'' +
                ", eventNames=" + eventNames +
                ", commandNames=" + commandNames +
                ", registed=" + registed +
                ", commandQueueName='" + commandQueueName + '\'' +
                ", expireTime=" + expireTime +
                ", ignoredEvent=" + ignoredEvent +
                '}';
    }
}
