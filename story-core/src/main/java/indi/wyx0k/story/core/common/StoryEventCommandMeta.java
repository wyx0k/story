package indi.wyx0k.story.core.common;

import java.util.Collection;
import java.util.Map;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
public interface StoryEventCommandMeta {
    /**
     * 注册时获取服务的名称
     * @return
     */
    String getServiceName();

    /**
     * 注册时获取这个微服务能够触发的事件
     * @return
     */
    Collection<String> getEventNames();

    /**
     * 注册时获取这个微服务支持的命令
     * @return
     */
    Collection<String> getCommandNames();

    /**
     * 判断是否已在引擎上注册
     * @return
     */
    Boolean getRegisted();

    /**
     * 获取将要监听的队列名
     * @return
     */
    String getCommandQueueName();

    /**
     * 获取与引擎失去连接后的超时时间,超时就将自己从注册中心上注销
     * @return
     */
    Long getExpireTime();

    /**
     * 协同时各个业务中需要忽略的事件
     * @return
     */
    Map<String,StoryEvent> ignoredEvent();

}
