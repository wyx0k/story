package indi.wyx0k.story.core.common;

import indi.wyx0k.story.core.constant.EventType;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
public interface StoryEvent {
    /**
     * 事件名称
     * @return
     */
    String getEventName();
    void setEventName(String eventName);

    /**
     * 事件所属服务的名称
     * @return
     */
    String getServiceName();
    void setServiceName(String serviceName);

    /**
     * 获取story环境,包含相应的数据
     * @return
     */
    StoryContext getStoryContext();
    void setStoryContext(StoryContext storyContext);
    /**
     * 获取事件的类型包括成功和失败两种
     * @return
     */
    EventType getEventType();
    void setEventType(EventType eventType);

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
