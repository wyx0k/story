package indi.wyx0k.story.core.common;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
public interface StoryContext {
    /**
     * 所有数据使用json序列化,通过数据名称获取数据
     * @param dataName
     * @return
     */
    String getData(String dataName);
    void putData(String dataName,String data);

    /**
     * 获取上一个微服务的返回结果
     * @return
     */
    String getLastResponse();
    void setLastResponse(String response);
}
