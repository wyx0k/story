package indi.wyx0k.story.core.common.impl;

import indi.wyx0k.story.core.common.StoryEventCommandMeta;

import java.util.Collection;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
public class SimpleStoryEventCommandMeta implements StoryEventCommandMeta {
    @Override
    public String getServiceName() {
        return null;
    }

    @Override
    public Collection<String> getEventNames() {
        return null;
    }

    @Override
    public Collection<String> getCommandNames() {
        return null;
    }

    @Override
    public Boolean getRegisted() {
        return null;
    }

    @Override
    public String getCommandQueueName() {
        return null;
    }

    @Override
    public Long getExpireTime() {
        return null;
    }
}
