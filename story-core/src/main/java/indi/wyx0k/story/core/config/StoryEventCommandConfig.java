package indi.wyx0k.story.core.config;

import indi.wyx0k.story.core.annotation.Command;
import indi.wyx0k.story.core.annotation.EventCommand;
import indi.wyx0k.story.core.annotation.ReturnEvent;
import indi.wyx0k.story.core.annotation.StoryController;
import indi.wyx0k.story.core.common.*;
import indi.wyx0k.story.core.common.impl.*;
import indi.wyx0k.story.core.constant.StoryEngineConstant;
import lombok.Data;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * story
 * --
 * 在这向协同引擎发送注册的事件和命令
 * @author wyx
 * --
 * 2020/4/10
 */
@Configuration
public class StoryEventCommandConfig {
    @Autowired
    private StoryEngineConstant storyEngineConstant;
    @Autowired
    private ConfigurableListableBeanFactory beanFactory;
    @Autowired BeanFactory beanFactory1;
    @Bean
    private StoryEventCommandManager storyEventCommandManager(){
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

        controllers.forEach((name,obj)->{
            Method[] methods = obj.getClass().getMethods();
            Arrays.stream(methods).forEach(method -> {
                if(method.isAnnotationPresent(ReturnEvent.class)){
                    ReturnEvent returnEvent = method.getAnnotation(ReturnEvent.class);
                    String eventName = returnEvent.value();
                    addStoryEvent(eventName,storyEventMap);
                }
                if(method.isAnnotationPresent(Command.class)){
                    Command command = method.getAnnotation(Command.class);
                    String commandName = command.value();
                    addStoryCommand(commandName,storyCommandMap);
                    addStoryCommandHandler(commandName,method,obj,storyCommandHandlerMap);
                }
                if(method.isAnnotationPresent(EventCommand.class)){
                    EventCommand eventCommand = method.getAnnotation(EventCommand.class);
                    String eventName = eventCommand.value();
                    String commandName = eventCommand.value();
                    addStoryEvent(eventName,storyEventMap);
                    addStoryCommand(commandName,storyCommandMap);
                }
            });
        });
        //从adapter中获取handler
        Map<String, StoryCommandHandlerConfigAdapter> adapters = beanFactory.getBeansOfType(StoryCommandHandlerConfigAdapter.class);
        adapters.forEach((s, adapter) -> {
            Map<String,StoryCommandHandler> simpleStoryCommandHandlers = adapter.storyCommandHandlers();
            simpleStoryCommandHandlers.forEach((k,v)->{
                storyCommandHandlerMap.put(k,v);
            });

        });

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
    private void addStoryCommandHandler(String commandName,Method method, Object owner,Map<String,StoryCommandHandler> map){
        SimpleStoryCommandHandler storyCommandHandler = new SimpleStoryCommandHandler();
        storyCommandHandler.setMethod(method);
        storyCommandHandler.setOwner(owner);
        map.put(commandName,storyCommandHandler);
    }
}
