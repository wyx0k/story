package indi.wyx0k.story.engine.controller;

import indi.wyx0k.story.core.common.impl.SimpleStoryEventCommandMeta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/13
 */
@Slf4j
@RestController
@RequestMapping("/simple")
public class SimpleModeController {
    public static final String SERVER_PREFIX = "simple_";
    @PostMapping("/register")
    public SimpleStoryEventCommandMeta register(@RequestBody SimpleStoryEventCommandMeta simpleStoryEventCommandMeta){
        log.info("接收到注册请求:{}",simpleStoryEventCommandMeta.getServiceName());
        log.info("接收到注册请求体:{}",simpleStoryEventCommandMeta);
        simpleStoryEventCommandMeta.setExpireTime(60*1000L);
        List<String> eventnames = (List<String>) simpleStoryEventCommandMeta.getEventNames();
        List<String> commandnames = (List<String>) simpleStoryEventCommandMeta.getCommandNames();

        eventnames.stream().forEach(s ->{
            log.info("event----> {}",s);
        });
        commandnames.stream().forEach(s ->{
            log.info("command----> {}",s);
        });
        simpleStoryEventCommandMeta.setCommandQueueName(SERVER_PREFIX+simpleStoryEventCommandMeta.getServiceName());
        Map<String, List<String>> ignoredEvent = new HashMap<>();

        simpleStoryEventCommandMeta.setIgnoredEvent(ignoredEvent);
        simpleStoryEventCommandMeta.setRegisted(true);
        return simpleStoryEventCommandMeta;
    }
}
