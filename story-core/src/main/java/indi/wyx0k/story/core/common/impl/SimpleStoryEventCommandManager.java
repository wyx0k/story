package indi.wyx0k.story.core.common.impl;

import indi.wyx0k.story.core.common.*;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
@Setter
public class SimpleStoryEventCommandManager implements StoryEventCommandManager {
    private SimpleStoryEventPulisher simpleStoryEventPulisher;
    private Map<String,StoryEvent> events;
    private Map<String,StoryCommand> commands;
    private Map<String,StoryCommandHandler> commandHandlers;
    private Map<String,List<String>> ignoredEvents;

    @Override
    public List<StoryCommand> listCommand() {
        return commands.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
    }

    @Override
    public StoryCommand getCommand(String name) {
        return commands.get(name);
    }

    @Override
    public Boolean surportedCommand(String name) {
        return commands.containsKey(name);
    }

    @Override
    public StoryCommandHandler getCommandHandler(String commandName) {
        return commandHandlers.get(commandName);
    }

    @Override
    public StoryContext handleCommand(String commandName, StoryContext storyContext) {
        StoryCommandHandler storyCommandHandler = commandHandlers.get(commandName);
        StoryContext result = storyCommandHandler.handleCommand(storyContext);
        return result;
    }

    @Override
    public StoryContext handleCommand(StoryContext storyContext, StoryCommandHandler storyCommandHandler) {
        StoryContext result = storyCommandHandler.handleCommand(storyContext);
        return result;
    }

    @Override
    public void publishEvent(StoryEvent storyEvent, StoryContext storyContext) {
        String processId = storyEvent.getProcessId();
        List<String> ignoredEventsList = ignoredEvents.get(processId);
        if(null != ignoredEventsList && ignoredEventsList.contains(storyEvent.getEventName())){
            storyEvent.setStoryContext(null);
        }else {
            storyEvent.setStoryContext(storyContext);
        }
        simpleStoryEventPulisher.publish(storyEvent);
    }

}
