package indi.wyx0k.story.core.config;

import com.alibaba.fastjson.JSON;
import indi.wyx0k.story.core.annotation.Command;
import indi.wyx0k.story.core.annotation.EventCommand;
import indi.wyx0k.story.core.annotation.ReturnEvent;
import indi.wyx0k.story.core.annotation.StoryController;
import indi.wyx0k.story.core.common.*;
import indi.wyx0k.story.core.common.impl.*;
import indi.wyx0k.story.core.constant.StoryEngineConstant;
import indi.wyx0k.story.core.exeception.StoryEngineClientInitialExeception;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * story
 * --
 * 在这向协同引擎发送注册的事件和命令
 * @author wyx
 * --
 * 2020/4/10
 */
@Slf4j
@Configuration
@ConditionalOnProperty(value = "story.engine.client.mode",havingValue = "simple")
public class StoryEventCommandConfig {
    @Autowired
    private StoryEngineConstant storyEngineConstant;
    @Autowired
    private ConfigurableListableBeanFactory beanFactory;
    @Autowired BeanFactory beanFactory1;
    @Bean
    public StoryEventCommandManager storyEventCommandManager(){
        SimpleStoryEventCommandMeta simpleStoryEventCommandMeta = new SimpleStoryEventCommandMeta();
        SimpleStoryEventCommandManager simpleStoryEventCommandManager = new SimpleStoryEventCommandManager();
        //设置协同引擎地址以及设置publisher
        SimpleStoryEventPulisher simpleStoryEventPulisher = new SimpleStoryEventPulisher();
        simpleStoryEventPulisher.setEngineLocation(storyEngineConstant.getLocation());
        simpleStoryEventCommandManager.setSimpleStoryEventPulisher(simpleStoryEventPulisher);
        //获取本微服务所支持的事件和命令
            //获取controller里被注解的事件和命令
        Map<String, Object> controllers = beanFactory.getBeansWithAnnotation(StoryController.class);
        Map<String, StoryEvent> storyEventMap = new HashMap<>();
        Map<String, StoryCommand> storyCommandMap = new HashMap<>();
        Map<String, StoryCommandHandler> storyCommandHandlerMap = new HashMap<>();
        simpleStoryEventCommandManager.setEvents(storyEventMap);
        simpleStoryEventCommandManager.setCommands(storyCommandMap);
        simpleStoryEventCommandManager.setCommandHandlers(storyCommandHandlerMap);
        controllers.forEach((name,obj)->{
            log.info("检测到待注册的StoryController --> {}",name);
            Method[] methods = obj.getClass().getDeclaredMethods();
            Arrays.stream(methods).forEach(method -> {
                ReturnEvent returnEvent = AnnotationUtils.findAnnotation(method,ReturnEvent.class);
                if(null != returnEvent){
                    String eventName = returnEvent.value();
                    addStoryEvent(eventName,storyEventMap);
                }
                Command command = AnnotationUtils.findAnnotation(method,Command.class);
                if(null != command){
                    String commandName = command.value();
                    Integer index = command.order();
                    addStoryCommand(commandName,storyCommandMap);
                    addStoryCommandHandler(commandName,method,obj,index,storyCommandHandlerMap);
                }
                EventCommand eventCommand = AnnotationUtils.findAnnotation(method,EventCommand.class);
                if(null != eventCommand){
                    String eventName = eventCommand.value();
                    String commandName = eventCommand.value();
                    addStoryEvent(eventName,storyEventMap);
                    addStoryCommand(commandName,storyCommandMap);
                    addStoryCommandHandler(commandName,method,obj,0,storyCommandHandlerMap);
                }
            });
        });
        //从adapter中获取handler
        Map<String, StoryCommandHandlerConfigAdapter> adapters = beanFactory.getBeansOfType(StoryCommandHandlerConfigAdapter.class);
        adapters.forEach((s, adapter) -> {
            Map<String,StoryCommandHandler> simpleStoryCommandHandlers = adapter.storyCommandHandlers();
            simpleStoryCommandHandlers.forEach((k,v)->{
                if(storyCommandHandlerMap.containsKey(k)){
                    log.warn("出现了重复的命令处理器:[{}],处理结果:覆盖",k);
                }
                storyCommandHandlerMap.put(k,v);
                addStoryCommand(k,storyCommandMap);
            });

        });
        //构建元信息
        log.info("event -->total: {}",storyEventMap.size());
        log.info("command -->total: {}",storyCommandMap.size());
        simpleStoryEventCommandMeta.setServiceName(storyEngineConstant.getApplicationName());
        simpleStoryEventCommandMeta.setEventNames(storyEventMap.entrySet().stream().map(entry -> entry.getKey()).collect(Collectors.toList()));
        simpleStoryEventCommandMeta.setCommandNames(storyCommandMap.entrySet().stream().map(entry -> entry.getKey()).collect(Collectors.toList()));
        simpleStoryEventCommandMeta.setRegisted(false);
        //向引擎注册本微服务的所有命令和事件
        simpleStoryEventCommandMeta = register(simpleStoryEventCommandMeta);
        simpleStoryEventCommandManager.setSimpleStoryEventCommandMeta(simpleStoryEventCommandMeta);
        if(!simpleStoryEventCommandMeta.getRegisted()){
            log.warn("协同引擎注册失败!");
            throw new StoryEngineClientInitialExeception("协同引擎注册失败!");
        }
        simpleStoryEventCommandManager.setIgnoredEvents(simpleStoryEventCommandMeta.getIgnoredEvent());
        return simpleStoryEventCommandManager;
    }
    private void addStoryEvent(String eventName,Map<String,StoryEvent> map){
        StoryEvent storyEvent = new SimpleStoryEvent();
        storyEvent.setEventName(eventName);
        storyEvent.setServiceName(storyEngineConstant.getApplicationName());
        map.put(eventName,storyEvent);
    }
    private void addStoryCommand(String commandName,Map<String,StoryCommand> map){
        StoryCommand storyCommand = new SimpleStoryCommand();
        storyCommand.setCommandName(commandName);
        storyCommand.setServiceName(storyEngineConstant.getApplicationName());
        map.put(commandName,storyCommand);
    }
    private void addStoryCommandHandler(String commandName,Method method, Object owner,Integer index,Map<String,StoryCommandHandler> map){
        SimpleStoryCommandHandler storyCommandHandler = null;
        if(map.containsKey(commandName)){
            StoryCommandHandler tmpHandler = map.get(commandName);
            if(!(tmpHandler instanceof SimpleStoryCommandHandler)){
                throw new StoryEngineClientInitialExeception("已经存在非注解形式定义的命令处理:"+commandName+"这与注解形式定义的默认命令处理器冲突,请检查");
            }
            storyCommandHandler = (SimpleStoryCommandHandler)tmpHandler;
        }else {
            storyCommandHandler = new SimpleStoryCommandHandler();
        }
        storyCommandHandler.addHolder(owner,method,index);
        map.put(commandName,storyCommandHandler);
    }
    private SimpleStoryEventCommandMeta register (SimpleStoryEventCommandMeta simpleStoryEventCommandMeta){
        RestTemplate template = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = JSON.toJSONString(simpleStoryEventCommandMeta);
        HttpEntity httpEntity = new HttpEntity(requestJson,httpHeaders);
        String result = template.postForObject(storyEngineConstant.getLocation()+"/simple/register",httpEntity,String.class);
        SimpleStoryEventCommandMeta re = JSON.parseObject(result,SimpleStoryEventCommandMeta.class);
        return re;
    }
}
